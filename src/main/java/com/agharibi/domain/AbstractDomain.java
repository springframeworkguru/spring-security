package com.agharibi.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class AbstractDomain implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private Date dateCreated;
    private Date lastUpdated;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {

    }

    @PreUpdate
    @PrePersist
    public void upateTimeStamps() {
        lastUpdated = new Date();
        if(dateCreated == null) {
            dateCreated = new Date();
        }
    }
}
