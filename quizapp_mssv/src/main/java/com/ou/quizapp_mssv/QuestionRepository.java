/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.quizapp_mssv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class QuestionRepository {
    public boolean addQuestion(Connection conn, Question question) throws SQLException{
        String sql = "INSERT INTO question(content, hint, image, category_id, level_id)" + "VALUES(?, ?, ?, ?, ?)";
        
        int category_id = getCategoryId(question.getCategory());
        int level_id = getLevelId(question.getLevel());
        
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, question.getContent());
        stm.setString(2, question.getHint());
        stm.setString(3, question.getImage());
        stm.setInt(4, category_id);
        stm.setInt(5, level_id);
        
        stm.executeUpdate();
        
        ResultSet rs = stm.getGeneratedKeys();
        int question_id = -1;
        if(rs.next()){
            question_id = rs.getInt(1);
        }
        else{
            return false;
        }
        
        for(Choice choice: question.getChoices()){
            insertChoice(conn, choice, question_id);
            
        }
        return true;
    }

    private void insertChoice(Connection conn, Choice choice, int question_id) throws SQLException {
        String sql = "INSERT INTO choice(content,is_correct,question_id)" + "VALUES(?, ?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, choice.getContent());
        stm.setBoolean(2, choice.isIsCorrect());
        stm.setInt(3, question_id);
        
        stm.executeQuery();
    }

    private Integer getCategoryId(String category) {
        return switch (category) {
            case "Grammar" -> 1;
            case "Vocabulary" -> 2;
            case "Reading" -> 3;
            default -> null;       
        };
    }

    private Integer getLevelId(String level) {
        return switch (level) {
            case "Easy" -> 1;
            case "Medium" -> 2;
            case "Hard" -> 3;
            default -> null;
        };
    }
}
