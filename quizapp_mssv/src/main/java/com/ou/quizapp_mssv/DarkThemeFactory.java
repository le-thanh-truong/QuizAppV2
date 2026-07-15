/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.quizapp_mssv;

/**
 *
 * @author admin
 */
public class DarkThemeFactory implements ThemeFactory {

    @Override
    public String getBackgroundStyle() {
        return "-fx-background-color:black; -fx-padding:20;";
    }

    @Override
    public String getButtonStyle() {
        return "-fx-font-size:14px;-fx-background-color: #00FF00;";
    }

    @Override
    public String getTitleStyle() {
        return "-fx-font-size:32px;-fx-font-weight:bold;-fx-text-fill:#FF0000;";
    }    
}
