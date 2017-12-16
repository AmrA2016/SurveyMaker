/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.SurveyAnswer;
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
public class SurveyAnswerModel {
    public static int save(SurveyAnswer obj) throws ClassNotFoundException, SQLException {
        int id = 0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "INSERT INTO survey_answer(ShowInfo,survey_id,user_id) VALUES('%s','%s','%s')";
        if(obj.isShowInfo())
            stmt.executeUpdate(String.format(query, 1,obj.getSurvey_id(),obj.getUser_id()) 
                    ,Statement.RETURN_GENERATED_KEYS);
        else
            stmt.executeUpdate(String.format(query, 0,obj.getSurvey_id(),obj.getUser_id()) 
                    ,Statement.RETURN_GENERATED_KEYS);
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        
        con.close();

        return id;
    }
    
    public static ArrayList<SurveyAnswer> getBySurveyID(int survey_id) {
        ArrayList<SurveyAnswer> surveyAnswers = new ArrayList<SurveyAnswer>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
            Statement stmt = (Statement) con.createStatement();

            String query = "SELECT * FROM survey_answer WHERE survey_id='%s'";

            ResultSet rs = stmt.executeQuery(String.format(query, survey_id));

            while (rs.next()) {
                SurveyAnswer surveyAnswer = new SurveyAnswer(rs.getInt(1), rs.getBoolean(2),rs.getInt(3), rs.getInt(4));
                surveyAnswers.add(surveyAnswer);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return surveyAnswers;
    }
}
