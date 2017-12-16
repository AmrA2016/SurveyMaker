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
public class Answer {
    private int id;
    private String answerContent;
    private int question_id;
    private int survey_answer_id;

    public Answer(int id, String answerContent, int question_id, int survey_answer_id) {
        this.id = id;
        this.answerContent = answerContent;
        this.question_id = question_id;
        this.survey_answer_id = survey_answer_id;
    }

    public Answer(String answerContent, int question_id, int survey_answer_id) {
        this.answerContent = answerContent;
        this.question_id = question_id;
        this.survey_answer_id = survey_answer_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getSurvey_answer_id() {
        return survey_answer_id;
    }

    public void setSurvey_answer_id(int survey_answer_id) {
        this.survey_answer_id = survey_answer_id;
    }
    
    
}
