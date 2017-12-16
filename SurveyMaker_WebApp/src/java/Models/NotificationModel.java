/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.Notification;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author osman
 */
public class NotificationModel {
     public static int save(Notification obj) throws ClassNotFoundException, SQLException {
        int id = 0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "INSERT INTO notification(content) "
                + "Values('%s')";
        stmt.executeUpdate(String.format(query, obj.getContent()),
                 Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();

        con.close();

        return id;
    }
     
    public static Notification getById(int notification_id) throws ClassNotFoundException, SQLException{
        Notification notification = null;
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        
        String query = "SELECT * FROM notification WHERE id = '%s'";
        
        ResultSet rs = stmt.executeQuery(String.format(query, notification_id));
        
        if(rs.next()){
            notification = new Notification(notification_id,rs.getString(2));
        }
        
        rs.close();

        con.close();
        return notification;
    }
}
