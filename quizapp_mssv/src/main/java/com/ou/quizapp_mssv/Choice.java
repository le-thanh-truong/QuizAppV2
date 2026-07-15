/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.quizapp_mssv;

/**
 *
 * @author admin
 */
public class Choice {
    private int id;
    private String content;
    private boolean isCorrect;

    public Choice(int id, String content, boolean isCorrect) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
    }
    
    public Choice(String content, boolean isCorrect) {
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
