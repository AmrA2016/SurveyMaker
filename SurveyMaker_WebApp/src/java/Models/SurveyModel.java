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

}
