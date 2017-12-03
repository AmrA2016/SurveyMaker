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
    private String mobileNumber;
    private String type;
    private boolean suspended;
    private boolean verified;

    public User() 
    {
        id = -1;
        firstName = "";
        lastName = "";
        mail = "";
        mobileNumber = "";
        type = "user";
        suspended = false;
        verified = false;
    }
    
    
    public User(int id, String firstName, String lastName, String mail, String mobileNumber, String type, boolean suspended, boolean verified) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.mobileNumber = mobileNumber;
        this.type = type;
        this.suspended = suspended;
        this.verified = verified;
    }

    public User(String firstName, String lastName, String mail, String mobileNumber, String type, boolean suspended, boolean verified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.mobileNumber = mobileNumber;
        this.type = type;
        this.suspended = suspended;
        this.verified = verified;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
