/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.Survey;
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
public class SurveyModel {

    /**
     *
     * @param obj
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int save(Survey obj) throws ClassNotFoundException, SQLException {
        int id = 0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "INSERT INTO survey(title,creationDate,creator_id) VALUES('%s','%s','%s')";
        stmt.executeUpdate(String.format(query, obj.getTitle(),obj.getCreationDate(),obj.getCreator_id()) 
                ,Statement.RETURN_GENERATED_KEYS);
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        
        con.close();

        return id;
    }
    
    public static String suspend(Integer surveyID) throws ClassNotFoundException, SQLException {
        int id = 0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "UPDATE survey SET suspended=1 WHERE id='%s'";
        stmt.executeUpdate(String.format(query, surveyID) 
                ,Statement.RETURN_GENERATED_KEYS);
        
        
        
        con.close();

        return "done";
    }
    
    public static String unSuspend(Integer surveyID) throws ClassNotFoundException, SQLException {
        int id = 0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "UPDATE survey SET suspended=0 WHERE id='%s'";
        stmt.executeUpdate(String.format(query, surveyID) 
                ,Statement.RETURN_GENERATED_KEYS);
        
        
        
        con.close();

        return "done";
    }
    
    public static String remove(Integer surveyID) throws ClassNotFoundException, SQLException {
        int id = 0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "DELETE from survey WHERE id='%s'";
        stmt.executeUpdate(String.format(query, surveyID) 
                ,Statement.RETURN_GENERATED_KEYS);
        
        
        
        con.close();

        return "done";
    }
    
    public static ArrayList<Survey> getAll() {
        ArrayList<Survey> surveys = new ArrayList<Survey>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
            Statement stmt = (Statement) con.createStatement();

            String query = "SELECT * FROM survey";

            ResultSet rs = stmt.executeQuery(String.format(query));

            while (rs.next()) {
                Survey survey = new Survey(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5));
                surveys.add(survey);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return surveys;
    }
    
    public static ArrayList<Survey> getAllByUserID(int user_id) {
        ArrayList<Survey> surveys = new ArrayList<Survey>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
            Statement stmt = (Statement) con.createStatement();

            String query = "SELECT * FROM survey WHERE creator_id='%s'";

            ResultSet rs = stmt.executeQuery(String.format(query,user_id));

            while (rs.next()) {
                Survey survey = new Survey(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5));
                surveys.add(survey);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return surveys;
    }
}
