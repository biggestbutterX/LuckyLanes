/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.test;

/**
 *
 * @author jorda
 */
public class Option {
    
    private Question question;
    private String text;
    private boolean checked;
    
    public Option(Question question, String text){
        this.question = question;
        this.text = text;
    }
    
    public boolean writeToDB(){
        /* Write to database, return true if successful */
        // insert/update question row in database
        // What table does this information go in?
        // How do we want the table to layout
        // Best functionality?
        return false;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    
}
