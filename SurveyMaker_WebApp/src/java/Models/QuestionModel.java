/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.Question;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class QuestionModel {

    public static int save(Question obj) throws ClassNotFoundException, SQLException {
        int id = -1;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "INSERT INTO question(content,type,survey_id) VALUES('%s','%s','%s')";
        stmt.executeUpdate(String.format(query, obj.getContent(), obj.getType(), obj.getSurvey_id()),
                 Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();

        con.close();

        return id;
    }

    public static ArrayList<Question> getBySurveyID(int survey_id) {
        ArrayList<Question> questions = new ArrayList<Question>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
            Statement stmt = (Statement) con.createStatement();

            String query = "SELECT * FROM question WHERE survey_id='%s'";

            ResultSet rs = stmt.executeQuery(String.format(query, survey_id));

            while (rs.next()) {
                Question question = new Question(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getInt(4));
                questions.add(question);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return questions;
    }
}
