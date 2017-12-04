/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.User;
import Entities.VerificationToken;
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
public class VerificationTokenModel {
    
    public static int save(VerificationToken obj) throws ClassNotFoundException, SQLException{
        int id = 0;
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root","") ;
         Statement stmt=(Statement) con.createStatement();
         String query = "INSERT INTO verfication_token(token, user_id) "
                           + "Values('%s','%s')";
         stmt.executeUpdate(String.format(query, obj.getToken(),obj.getUser_id())
                 ,Statement.RETURN_GENERATED_KEYS);
         
         ResultSet rs = stmt.getGeneratedKeys();
         if(rs.next()){
             id = rs.getInt(1);
         }
         rs.close();
         
         con.close();
         
        return id;
    }
    
    public static VerificationToken getByUserID(int user_id){
        VerificationToken token = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root","") ;
            Statement stmt=(Statement) con.createStatement();
            
            String query = "SELECT * FROM verfication_token WHERE user_id = '%s'";
            
            ResultSet rs = stmt.executeQuery(String.format(query, user_id));
            
            if(rs.next()){
                token = new VerificationToken(rs.getInt(1), rs.getString(2),rs.getInt(3));
                
            }
            
            rs.close();
            stmt.close();
            con.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return token;
    }
    
}
