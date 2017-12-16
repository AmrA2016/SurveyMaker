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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amr
 */
public class UserModel {

    public static int save(User obj) throws ClassNotFoundException, SQLException {
        int id = 0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "INSERT INTO user(firstName, lastName, mail, password, mobileNumber) "
                + "Values('%s','%s','%s','%s','%s')";
        stmt.executeUpdate(String.format(query, obj.getFirstName(), obj.getLastName(), obj.getMail(), obj.getPassword(), obj.getMobileNumber()),
                 Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();

        con.close();

        return id;
    }
    
    public static void notify(int user_id, int notificationID) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "INSERT INTO notification_user(user_id,notification_id) VALUES('%s','%s')";

        stmt.executeUpdate(String.format(query, user_id, notificationID));

        stmt.close();
        con.close();

    }
    
    public static ArrayList<Integer> getAllNormalUsers() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "SELECT ID FROM user WHERE admin=0";
        ResultSet RS = stmt.executeQuery(query);
        
        ArrayList<Integer> usersIDs = new ArrayList<Integer>();
        while(RS.next()){
            usersIDs.add(Integer.parseInt(RS.getString("ID")));
        }
        stmt.close();
        con.close();
        return usersIDs;

    }
    
    public static ArrayList<String> getAllNormalUsersMails() throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "SELECT mail FROM user WHERE admin=0";
        ResultSet RS = stmt.executeQuery(query);
        
        ArrayList<String> usersMails = new ArrayList<String>();
        while(RS.next()){
            usersMails.add(RS.getString("mail"));
        }
        stmt.close();
        con.close();
        return usersMails;

    }
    
    public static ArrayList<Integer> getAllAdmins() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "SELECT ID FROM user WHERE admin=1";
        ResultSet RS = stmt.executeQuery(query);
        
        ArrayList<Integer> adminIDs = new ArrayList<Integer>();
        while(RS.next()){
            adminIDs.add(Integer.parseInt(RS.getString("ID")));
        }
        stmt.close();
        con.close();
        return adminIDs;

    }

    public static User getByID(int id) {
        User user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
            Statement stmt = (Statement) con.createStatement();

            String query = "SELECT * FROM user WHERE id = '%s'";

            ResultSet rs = stmt.executeQuery(String.format(query, id));

            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9));

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

    public static User getByMail(String mail) {
        User user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
            Statement stmt = (Statement) con.createStatement();

            String query = "SELECT * FROM user WHERE mail = '%s'";

            ResultSet rs = stmt.executeQuery(String.format(query, mail));

            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9));

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

    public static void setPasswrod(int user_id, String val) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "UPDATE user SET password = '%s' WHERE id = '%s' ";

        stmt.executeUpdate(String.format(query, val, user_id));

        stmt.close();
        con.close();

    }
    
    public static void changePasswrod(int user_id, String newPassword) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "UPDATE user SET password = '%s' WHERE ID = '%s' ";

        stmt.executeUpdate(String.format(query, newPassword, user_id));
        
        stmt.close();
        con.close();

    }

    public static void setVerified(int user_id, boolean val) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "UPDATE user SET verified = '%s' WHERE id = '%s' ";

        if (val) {
            stmt.executeUpdate(String.format(query, 1, user_id));
        } else {
            stmt.executeUpdate(String.format(query, 0, user_id));
        }

        stmt.close();
        con.close();

    }
    public static void setAdmin(int user_id) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "UPDATE user SET admin = '%s' WHERE id = '%s' ";

        
            stmt.executeUpdate(String.format(query, 1, user_id));
        

        stmt.close();
        con.close();

    }
    public static void setSuspended(int user_id) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "UPDATE user SET suspended = '%s' WHERE id = '%s' ";

        
            stmt.executeUpdate(String.format(query, 1, user_id));
        

        stmt.close();
        con.close();

    }
    public static void setUnSuspended(int user_id) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "UPDATE user SET suspended = '%s' WHERE id = '%s' ";

        
            stmt.executeUpdate(String.format(query, 0, user_id));
        

        stmt.close();
        con.close();

    }
   
}
