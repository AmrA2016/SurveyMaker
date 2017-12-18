/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.Report;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Amr
 */
public class ReportModel {
    
    public static int save(Report report) throws ClassNotFoundException, SQLException {
        int id = 0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "INSERT INTO report(content,survey_id,user_id) VALUES('%s','%s','%s')";
        stmt.executeUpdate(String.format(query, report.getContent(),report.getSurvey_id(),report.getUser_id()) 
                ,Statement.RETURN_GENERATED_KEYS);
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        
        con.close();

        return id;
    }
    
    public static ArrayList<Report> getAll() throws ClassNotFoundException, SQLException{
        ArrayList<Report> reports = new ArrayList<>();
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();
        String query = "SELECT * FROM report";
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            Report report = new Report(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            reports.add(report);
        }
        rs.close();
        stmt.close();
        con.close();
        
        return reports;
    }
}
