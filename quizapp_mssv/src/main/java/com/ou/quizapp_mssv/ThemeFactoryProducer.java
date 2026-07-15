/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.quizapp_mssv;

/**
 *
 * @author admin
 */
public class ThemeFactoryProducer {
    public static ThemeFactory getFactory(ThemeType themeType){
        return switch(themeType){
            case DEFAULT_THEME -> new DefaultThemeFactory();
            case DARK_THEME -> new DarkThemeFactory();
            case LIGHT_THEME -> new LightThemeFactory();
            default -> new DefaultThemeFactory();
        };
    }
}
