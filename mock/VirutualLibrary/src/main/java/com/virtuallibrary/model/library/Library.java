package com.virtuallibrary.model.library;
import com.virtuallibrary.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Library extends BaseModel {

    private String libAdmin;
    private Date createDate;
    private Boolean active;

    public Library() {
    }

    public Library(Long id, String libAdmin, Date createDate, Boolean active) {
        this.id = id;
        this.libAdmin = libAdmin;
        this.createDate = createDate;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibAdmin() {
        return libAdmin;
    }

    public void setLibAdmin(String libAdmin) {
        this.libAdmin = libAdmin;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
