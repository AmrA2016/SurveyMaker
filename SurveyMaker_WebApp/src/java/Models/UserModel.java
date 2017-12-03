/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Amr
 */
public class UserModel {
    
    public static long saveSurvey(User obj) throws ClassNotFoundException, SQLException{
        long id = 0;
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root","") ;
         Statement stmt=(Statement) con.createStatement();
         String query = "INSERT INTO user(firstName, lastName, mail, mobileNumber, type) "
                           + "Values('%s','%s','%s','%s','%s')";
         stmt.executeUpdate(String.format(query, obj.getFirstName(),obj.getLastName(),obj.getMail(),obj.getMobileNumber(),obj.getType())
                 ,Statement.RETURN_GENERATED_KEYS);
         
         ResultSet rs = stmt.getGeneratedKeys();
         if(rs.next()){
             id = rs.getInt(1);
         }
         rs.close();
         
         con.close();
         
        return id;
    }
}
