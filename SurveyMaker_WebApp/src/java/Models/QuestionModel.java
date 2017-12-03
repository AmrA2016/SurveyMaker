/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.question;
import Entities.survey;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class QuestionModel {
    
     public static void savequestion(question obj) throws ClassNotFoundException, SQLException{
        long id = 0;
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root","") ;
         Statement stmt=(Statement) con.createStatement();
         stmt.executeUpdate("INSERT INTO question(content) VALUES('"+obj.getContent()+"')");
         
         con.close();
         
       
    }
    
}
