/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.ou.quizapp_mssv;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class Quizapp_mssv extends Application {

    private ThemeFactory currTheme;
    private ThemeType currentThemeType = ThemeType.DEFAULT_THEME;
    private Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        DatabaseIniti.initDB();

        this.mainStage = stage;
        currTheme = ThemeFactoryProducer.getFactory(currentThemeType);
        showHome();
    }

    private void showHome() {
        Label title = new Label("QUIZAPP - MSSV.");
        title.setStyle(currTheme.getTitleStyle());

        Button btnQuestion = new Button("Quản lý câu hỏi");
        btnQuestion.setPrefWidth(250);
        btnQuestion.setStyle(currTheme.getButtonStyle());

        btnQuestion.setOnAction(e -> showQuestion());

        Button btnPractice = new Button("Luyện tập");
        btnPractice.setPrefWidth(250);
        btnPractice.setStyle(currTheme.getButtonStyle());

        btnPractice.setOnAction(e -> MyAlert.getInstance()
                .showMessage("Đây là chức năng luyện tập"));

        Button btnExam = new Button("Luyện thi");
        btnExam.setPrefWidth(250);
        btnExam.setOnAction(e -> MyAlert.getInstance()
                .showMessage("Đây là chức năng luyện thi"));
        btnExam.setStyle(currTheme.getButtonStyle());

        ComboBox<ThemeType> cbTheme = new ComboBox<>();
        cbTheme.getItems().addAll(ThemeType.DEFAULT_THEME,
                ThemeType.DARK_THEME, ThemeType.LIGHT_THEME);

        cbTheme.setValue(currentThemeType);
        cbTheme.setPrefWidth(250);
        cbTheme.setOnAction(e -> {
            currentThemeType = cbTheme.getValue();
            currTheme = ThemeFactoryProducer.getFactory(currentThemeType);
            showHome();
        });

        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle(currTheme.getBackgroundStyle());
        root.getChildren().addAll(title, btnQuestion,
                btnPractice, btnExam, cbTheme);

        Scene scene = new Scene(root, 500, 300);
        this.mainStage.setScene(scene);
        this.mainStage.show();
    }

    private void showQuestion() {
        Label label = new Label("Quản lý câu hỏi");
        label.setStyle(currTheme.getTitleStyle());

        TextField txtContent = new TextField();
        txtContent.setPromptText("Nội dung câu hỏi...");
        txtContent.setMaxWidth(500);

        TextField txtHint = new TextField();
        txtHint.setPromptText("Gợi ý");
        txtHint.setMaxWidth(500);

        ComboBox<String> cbCates = new ComboBox<>();
        cbCates.getItems().addAll("Danh mục", "Grammar", "Vocabulary", "Reading");
        cbCates.setValue("Danh mục");

        ComboBox<String> cbLevel = new ComboBox<>();
        cbLevel.getItems().addAll("Độ khó", "Easy", "Medium", "Hard");
        cbLevel.setValue("Độ khó");

        HBox hbCate = new HBox(100, cbCates, cbLevel);
        hbCate.setAlignment(Pos.CENTER);
        
        
        ToggleGroup toogle = new ToggleGroup();
        RadioButton rdA = new RadioButton();
        rdA.setUserData("A");
        rdA.setToggleGroup(toogle);

        TextField txtAnswerA = new TextField();
        txtAnswerA.setPromptText("Nhập đáp án A");
        txtAnswerA.setMaxWidth(300);

        RadioButton rdB = new RadioButton();
        rdB.setUserData("B");
        rdB.setToggleGroup(toogle);

        TextField txtAnswerB = new TextField();
        txtAnswerB.setPromptText("Nhập đáp án B");
        txtAnswerB.setMaxWidth(300);
        
        HBox hbrow1 = new HBox(10, rdA, txtAnswerA, rdB, txtAnswerB);
        hbrow1.setAlignment(Pos.CENTER);

        RadioButton rdC = new RadioButton();
        rdC.setUserData("C");
        rdC.setToggleGroup(toogle);

        TextField txtAnswerC = new TextField();
        txtAnswerC.setPromptText("Nhập đáp án C");
        txtAnswerC.setMaxWidth(300);

        RadioButton rdD = new RadioButton();
        rdD.setUserData("D");
        rdD.setToggleGroup(toogle);

        TextField txtAnswerD = new TextField();
        txtAnswerD.setPromptText("Nhập đáp án D");
        txtAnswerD.setMaxWidth(300);
        
        HBox hbrow2 = new HBox(10, rdC, txtAnswerC, rdD, txtAnswerD);
        hbrow2.setAlignment(Pos.CENTER);
        
        Button back = new Button("Quay lại");
        back.setOnAction(eh -> showHome());
        back.setStyle(currTheme.getButtonStyle());
        
        Button addQuestion = new Button("Thêm câu hỏi");
        addQuestion.setOnAction(e -> {
           try {
               Connection connect = JdbcConnector.getInstance().connect();
               QuestionRepository questionRepo = new QuestionRepository();
               
               if(txtContent.getText().trim().isEmpty() || txtHint.getText().trim().isEmpty()){
                   MyAlert.getInstance().showError("Vui long nhap noi dung va goi y");
                   return;
               }
               
               if(cbCates.getValue() == null || cbLevel.getValue() == null){
                   MyAlert.getInstance().showError("Vui long chon danh muc va level");
                   return;
               }
               
               Choice a  = new Choice(txtAnswerA.getText().trim(), rdA.getUserData().equals("A"));
               Choice b  = new Choice(txtAnswerB.getText().trim(), rdB.getUserData().equals("B"));
               Choice c  = new Choice(txtAnswerC.getText().trim(), rdC.getUserData().equals("C"));
               Choice d  = new Choice(txtAnswerD.getText().trim(), rdD.getUserData().equals("D"));
               
               Question q = new Question.Builder().setCate(cbCates.getValue())
                        .setLevel(cbLevel.getValue())
                       .setHint(txtHint.getText())
                       .setContent(txtContent.getText().trim())
                       .addChoice(List.of(a,b,c,d))
                       .build();
               
               Question question = q.clone();
               
               boolean result = questionRepo.addQuestion(connect, question);
               if(result){
                   MyAlert.getInstance().showMessage("Them thanh cong");
               }
               else{
                   MyAlert.getInstance().showMessage("Them that bai");
               }
           } catch(SQLException ex){
               System.out.println("Error" + ex.getMessage());
           }
        });
        addQuestion.setStyle(currTheme.getButtonStyle());
        
        HBox hbrow3 =new HBox(10, back, addQuestion);
        hbrow3.setAlignment(Pos.CENTER);
        
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle(currTheme.getBackgroundStyle());
        root.getChildren().addAll(label, txtContent, txtHint, hbCate, hbrow1, hbrow2, hbrow3);

        Scene scene = new Scene(root, 500, 400);
        this.mainStage.setScene(scene);
        this.mainStage.show();
    }

}
