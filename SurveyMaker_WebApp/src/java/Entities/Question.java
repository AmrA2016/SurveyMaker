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
public class Question {
    private int id;
    private String content;
    private String type;
    private int survey_id;

    public Question() {
        id = -1;
        content = "";
        type = "";
        survey_id = -1;
    }

    public Question(int id, String content,String type, int survey_id) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.survey_id = survey_id;
    }

    public Question(String content,String type, int survey_id) {
        this.content = content;
        this.type = type;
        this.survey_id = survey_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }
    
    
}
