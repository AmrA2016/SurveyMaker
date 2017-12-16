/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Amr
 */
public class Choice {
    private int id;
    private String content;
    private int question_id;

    public Choice(int id, String content, int question_id) {
        this.id = id;
        this.content = content;
        this.question_id = question_id;
    }

    public Choice(String content, int question_id) {
        this.content = content;
        this.question_id = question_id;
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

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
            
    
    
}
