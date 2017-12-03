/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.mcq;
import Entities.open_question;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class openQuestionModel {
    
      public static void savequestion(open_question obj) throws ClassNotFoundException, SQLException{
       
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root","") ;
         Statement stmt=(Statement) con.createStatement();
         stmt.executeUpdate("INSERT INTO open_question VALUES('"+obj.getQuestionId()+"')");
         
         con.close();
         
       
    }
    
}
