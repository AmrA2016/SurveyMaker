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
public class SurveyAnswer {
    private int id;
    private boolean showInfo;
    private int survey_id;
    private int user_id;

    public SurveyAnswer(int id, boolean showInfo, int survey_id, int user_id) {
        this.id = id;
        this.showInfo = showInfo;
        this.survey_id = survey_id;
        this.user_id = user_id;
    }

    public SurveyAnswer(boolean showInfo, int survey_id, int user_id) {
        this.showInfo = showInfo;
        this.survey_id = survey_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isShowInfo() {
        return showInfo;
    }

    public void setShowInfo(boolean showInfo) {
        this.showInfo = showInfo;
    }

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
}
