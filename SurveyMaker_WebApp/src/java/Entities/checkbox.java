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

/**
 *
 * @author DELL
 */
@Entity
public class checkbox extends question{

  
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "ID", nullable = false)
    private int question_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
     public int getQuestionId() {
        return question_id;
    }

    public void setQuestionId(int question_id) {
        this.question_id = question_id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof checkbox)) {
            return false;
        }
        checkbox other = (checkbox) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.checkbox[ id=" + id + " ]";
    }
    
}
