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
public class Survey {
    private int id;
    private String title;
    private String creationDate;
    private boolean suspended;
    private int creator_id;

    public Survey() {
        id = -1;
        title = "";
        creationDate = "";
        suspended = false;
        creator_id = -1;
    }

    public Survey(int id, String title, String creationDate, boolean suspended, int creator_id) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.suspended = suspended;
        this.creator_id = creator_id;
    }

    public Survey(String title, String creationDate, int creator_id) {
        this.title = title;
        this.creationDate = creationDate;
        this.creator_id = creator_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }
    
    
}
