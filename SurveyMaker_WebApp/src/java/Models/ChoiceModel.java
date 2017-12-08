/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.Choice;
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
public class ChoiceModel {

    public static int save(Choice obj) throws ClassNotFoundException, SQLException {
        int id = -1;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
        Statement stmt = (Statement) con.createStatement();

        String query = "INSERT INTO choice(content,question_id) VALUES('%s','%s')";
        stmt.executeUpdate(String.format(query, obj.getContent(), obj.getQuestion_id()),
                 Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();

        con.close();

        return id;
    }
    
    public static ArrayList<Choice> getByQuestionID(int question_id) {
        ArrayList<Choice> choices = new ArrayList<Choice>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poll_surveyapp", "root", "");
            Statement stmt = (Statement) con.createStatement();

            String query = "SELECT * FROM choice WHERE question_id='%s'";

            ResultSet rs = stmt.executeQuery(String.format(query, question_id));

            while (rs.next()) {
                Choice choice = new Choice(rs.getInt(1),rs.getString(2), rs.getInt(3));
                choices.add(choice);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return choices;
    }
}
