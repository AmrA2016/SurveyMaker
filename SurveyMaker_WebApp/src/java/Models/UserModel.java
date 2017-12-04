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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amr
 */
public class UserModel {
    
    public static int save(User obj) throws ClassNotFoundException, SQLException{
        int id = 0;
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root","") ;
         Statement stmt=(Statement) con.createStatement();
         String query = "INSERT INTO user(firstName, lastName, mail, password, mobileNumber) "
                           + "Values('%s','%s','%s','%s','%s')";
         stmt.executeUpdate(String.format(query, obj.getFirstName(),obj.getLastName(),obj.getMail(),obj.getPassword(), obj.getMobileNumber())
                 ,Statement.RETURN_GENERATED_KEYS);
         
         ResultSet rs = stmt.getGeneratedKeys();
         if(rs.next()){
             id = rs.getInt(1);
         }
         rs.close();
         
         con.close();
         
        return id;
    }
    
    public static User getByID(int id){
        User user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root","") ;
            Statement stmt=(Statement) con.createStatement();
            
            String query = "SELECT * FROM user WHERE id = '%s'";
            
            ResultSet rs = stmt.executeQuery(String.format(query, id));
            
            if(rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                                   rs.getString(5),rs.getString(6),rs.getBoolean(7),rs.getBoolean(8),rs.getBoolean(9));
                
            }
            
            rs.close();
            stmt.close();
            con.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    public static User getByMail(String mail){
        User user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root","") ;
            Statement stmt=(Statement) con.createStatement();
            
            String query = "SELECT * FROM user WHERE mail = '%s'";
            
            ResultSet rs = stmt.executeQuery(String.format(query, mail));
            
            if(rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                                   rs.getString(5),rs.getString(6),rs.getBoolean(7),rs.getBoolean(8),rs.getBoolean(9));
                
            }
            
            rs.close();
            stmt.close();
            con.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
}
