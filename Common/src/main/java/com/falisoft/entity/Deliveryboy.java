/*
 * __NAME__.java
 *
 * Created on __DATE__, __TIME__
 *
 * Copyright(c) {2017} Falisoft, Inc.  All Rights Reserved.
 * This software is the proprietary information of Falisoft.
 *
 */

package com.falisoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 * 
 */
@Entity
@Table(name = "deliveryboy")
@NamedQueries({
    @NamedQuery(name = "Deliveryboy.findAll", query = "SELECT d FROM Deliveryboy d")})
public class Deliveryboy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "LEVEL")
    private Integer level;
    @Column(name = "RATING")
    private Integer rating;
    @Column(name = "STATUS")
    private Integer status;
    @Size(max = 45)
    @Column(name = "DRIVERLICENCENUMBER", length = 45)
    private String driverlicencenumber;
    @Size(max = 45)
    @Column(name = "ENGINEPLATENUMBER", length = 45)
    private String engineplatenumber;
    @Size(max = 45)
    @Column(name = "ENGINEMODEL", length = 45)
    private String enginemodel;
    @Size(max = 45)
    @Column(name = "ENGINETYPE", length = 45)
    private String enginetype;
    @Column(name = "LICENCEISSUEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date licenceissuedate;
    @Column(name = "LICENCEEXPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date licenceexpdate;
    @Size(max = 99)
    @Column(name = "REFERENCE", length = 99)
    private String reference;
    @Size(max = 99)
    @Column(name = "ADDRESS", length = 99)
    private String address;
    @OneToMany(mappedBy = "deliveredby", fetch = FetchType.LAZY)
    private Collection<Delivery> deliveryCollection;

    public Deliveryboy() {
        this.deliveryCollection = new ArrayList<>();
    }

    public Deliveryboy(String driverlicencenumber, String engineplatenumber, String enginemodel, String enginetype, Date licenceissuedate, Date licenceexpdate, String reference, String address) {
        this.driverlicencenumber = driverlicencenumber;
        this.engineplatenumber = engineplatenumber;
        this.enginemodel = enginemodel;
        this.enginetype = enginetype;
        this.licenceissuedate = licenceissuedate;
        this.licenceexpdate = licenceexpdate;
        this.reference = reference;
        this.address = address;
    }

    public Deliveryboy(Long id) {
        this.id = id;
         this.deliveryCollection = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDriverlicencenumber() {
        return driverlicencenumber;
    }

    public void setDriverlicencenumber(String driverlicencenumber) {
        this.driverlicencenumber = driverlicencenumber;
    }

    public String getEngineplatenumber() {
        return engineplatenumber;
    }

    public void setEngineplatenumber(String engineplatenumber) {
        this.engineplatenumber = engineplatenumber;
    }

    public String getEnginemodel() {
        return enginemodel;
    }

    public void setEnginemodel(String enginemodel) {
        this.enginemodel = enginemodel;
    }

    public String getEnginetype() {
        return enginetype;
    }

    public void setEnginetype(String enginetype) {
        this.enginetype = enginetype;
    }

    public Date getLicenceissuedate() {
        return licenceissuedate;
    }

    public void setLicenceissuedate(Date licenceissuedate) {
        this.licenceissuedate = licenceissuedate;
    }

    public Date getLicenceexpdate() {
        return licenceexpdate;
    }

    public void setLicenceexpdate(Date licenceexpdate) {
        this.licenceexpdate = licenceexpdate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<Delivery> getDeliveryCollection() {
        return deliveryCollection;
    }

    public void setDeliveryCollection(Collection<Delivery> deliveryCollection) {
        this.deliveryCollection = deliveryCollection;
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
        if (!(object instanceof Deliveryboy)) {
            return false;
        }
        Deliveryboy other = (Deliveryboy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.falisoft.entity.Deliveryboy[ id=" + id + " ]";
    }

}
