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
public class CheckboxQuestion extends Question {
    private int id;
    private int question_id;

    public CheckboxQuestion() {
    }

    public CheckboxQuestion(int id, int question_id) {
        this.id = id;
        this.question_id = question_id;
    }

    public CheckboxQuestion(int id, int question_id, String content, int survey_id) {
        super(content, survey_id);
        this.id = id;
        this.question_id = question_id;
    }

    public CheckboxQuestion(int question_id) {
        this.question_id = question_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
}
