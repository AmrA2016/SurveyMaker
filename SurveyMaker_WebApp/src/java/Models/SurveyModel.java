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
    
    public static int report(String content , Integer surveyID , Integer userID) throws ClassNotFoundException, SQLException {
        int id = 0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "INSERT INTO report(content,survey_id,user_id) VALUES('%s','%s','%s')";
        stmt.executeUpdate(String.format(query, content,surveyID,userID) 
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

}
