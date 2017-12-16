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
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private String mobileNumber;
    private boolean admin;
    private boolean suspended;
    private boolean verified;

    public User() 
    {
        id = -1;
        firstName = "";
        lastName = "";
        mail = "";
        password = "";
        mobileNumber = "";
        admin = false;
        suspended = false;
        verified = false;
    }

    public User(int id, String firstName, String lastName, String mail, String password, String mobileNumber, boolean admin, boolean suspended, boolean verified) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.admin = admin;
        this.suspended = suspended;
        this.verified = verified;
    }

    public User(String firstName, String lastName, String mail, String password, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.admin = false;
        this.suspended = false;
        this.verified = false;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    
    
}
