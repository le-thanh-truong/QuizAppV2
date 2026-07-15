/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.quizapp_mssv;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Question implements Cloneable {
    
    private int id;
    private String content;
    private String hint;
    private String image;
    private String category;
    private String level;
    private List<Choice> choices;

    public Question() {
    }

    public Question(int id, String content, String hint, String image, String category, String level, List<Choice> choices) {
        this.id = id;
        this.content = content;
        this.hint = hint;
        this.image = image;
        this.category = category;
        this.level = level;
        this.choices = choices;
    }
    
    public Question(String content, String hint, String image, String category, String level, List<Choice> choices) {
        this.content = content;
        this.hint = hint;
        this.image = image;
        this.category = category;
        this.level = level;
        this.choices = choices;
    }
    
    private Question(Builder b) {
        this.id = b.id;
        this.content = b.content;
        this.hint = b.hint;
        this.image = b.image;
        this.category = b.category;
        this.level = b.level;
        this.choices = b.choices;
    }
    
    public static class Builder {
        private int id;
        private String content;
        private String hint;
        private String image;
        private String category;
        private String level;
        private List<Choice> choices;
        
        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        
        public Builder setContent(String content) {
            this.content = content;
            return this;
        }
        
        public Builder setHint(String hint) {
            this.hint = hint;
            return this;
        }
        
        public Builder addChoice(List<Choice> c) {
            this.choices.addAll(c);
            return this;
        }
        
        public Builder setCate(String c) {
            this.category = c;
            return this;
        }
        
        public Builder setLevel(String lvl) {
            this.level = lvl;
            return this;
        }
        
        public Question build() {
            return new Question(this);
        }
    }
    
    
    
    @Override
    protected Question clone() {
        try {
            Question copy = (Question) super.clone();
            copy.setChoices(new ArrayList<>(this.getChoices()));
            return copy;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * @param hint the hint to set
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the choices
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * @param choices the choices to set
     */
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

}
