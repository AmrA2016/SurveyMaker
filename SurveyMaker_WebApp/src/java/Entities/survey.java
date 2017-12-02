/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

/**
 *
 * @author DELL
 */
@Entity
public class survey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @Column(name = "title")
    private String title;
    @Column(name = "url")
    private String url;
    @Column(name = "creationDate")
    private String creationDate;
     @Column(name = "suspended")
    private int suspended;
    
   @JoinColumn(name = "ID")
   private user user;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }
     public String getTitle() {
        return title;
    }
     public void setUrl(String url) {
        this.url = url;
    }
     public String getUrl() {
        return url;
    }
     public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
     public String getCreationDate() {
        return creationDate;
    }
     public void setSuspended(int suspended) {
        this.suspended = suspended;
    }
     public int getSuspended() {
        return suspended;
    } 
     
    public user getUserID() {
        return user;
    }

    public void setUserID(user user) {
        this.user = user;
    } 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ID != null ? ID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof survey)) {
            return false;
        }
        survey other = (survey) object;
        if ((this.ID == null && other.ID != null) || (this.ID != null && !this.ID.equals(other.ID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.survey[ id=" + ID + " ]";
    }
    
}
