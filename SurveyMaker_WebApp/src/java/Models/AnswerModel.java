/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.Answer;
import Entities.Question;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amr
 */
public class AnswerModel {
    public static int save(Answer obj) throws ClassNotFoundException, SQLException {
        int id = 0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "INSERT INTO answer(answerContent,question_id,survey_answer_id) VALUES('%s','%s','%s')";
        stmt.executeUpdate(String.format(query, obj.getAnswerContent(),obj.getQuestion_id(),obj.getSurvey_answer_id()) 
                ,Statement.RETURN_GENERATED_KEYS);
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        
        con.close();

        return id;
    }
    
    public static ArrayList<Answer> getByQuestionID(int question_id) {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
            Statement stmt = (Statement) con.createStatement();

            String query = "SELECT * FROM answer WHERE question_id='%s'";

            ResultSet rs = stmt.executeQuery(String.format(query, question_id));

            while (rs.next()) {
                Answer answer = new Answer(rs.getInt(1), rs.getString(2),rs.getInt(3), rs.getInt(4));
                answers.add(answer);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return answers;
    }
}
