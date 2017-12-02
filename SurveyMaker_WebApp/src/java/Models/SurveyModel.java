/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Entities.survey;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
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
    
    public long saveSurvey(survey obj) throws ClassNotFoundException, SQLException{
        long id = 0;
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root","") ;
         Statement stmt=(Statement) con.createStatement();
         stmt.executeUpdate("INSERT INTO survey VALUES('"+obj.getID()+"','"+obj.getTitle()+"','"+obj.getUrl()+"','"+obj.getCreationDate()+"','"+obj.getSuspended()+"','"+obj.getUserID()+"')");
         
        // stmt.execute("SELECT ID AS LastID FROM survey WHERE ID ='"+ @@Identity+"'");
         con.close();
         
        return id;
    }
    
    
    
}
