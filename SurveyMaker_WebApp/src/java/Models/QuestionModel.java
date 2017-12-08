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

/**
 *
 * @author DELL
 */
public class QuestionModel {
    
     public static int save(Question obj) throws ClassNotFoundException, SQLException{
        int id = -1;
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root","") ;
         Statement stmt=(Statement) con.createStatement();
         String query = "INSERT INTO question(content,type,survey_id) VALUES('%s','%s','%s')";
         stmt.executeUpdate(String.format(query, obj.getContent(),obj.getType(),obj.getSurvey_id())
                            ,Statement.RETURN_GENERATED_KEYS);
         
         
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
         
        con.close();
         
        return id;
    }
    
}
