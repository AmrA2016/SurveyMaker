/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Amr
 */
public class VerificationToken {
    private int id;
    private String token;
    private int user_id;

    public VerificationToken(int id, String token, int user_id) {
        this.id = id;
        this.user_id = user_id;
        this.token = token;
    }

    public VerificationToken( String token, int user_id) {
        this.user_id = user_id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}
