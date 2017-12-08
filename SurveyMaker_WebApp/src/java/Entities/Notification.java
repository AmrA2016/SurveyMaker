/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author osman
 */
public class Notification {

    private int id;
    private String content;
    
    public Notification(){
        this.id = -1;
        this.content = "non";
    }
    public Notification(int id , String content){
        this.id = id;
        this.content = content;
    }
    
    public Notification(String content){
        this.content = content;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
     public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    

}
