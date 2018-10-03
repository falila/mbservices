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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "delivery")
@NamedQueries({
    @NamedQuery(name = "Delivery.findAll", query = "SELECT d FROM Delivery d")})
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DELIVERYID", nullable = false)
    private Long deliveryid;
    @Column(name = "WHENASSIGNED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date whenassigned;
    @Column(name = "WHENDELIVERED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date whendelivered;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LONG", precision = 22)
    private Double long1;
    @Column(name = "LAT", precision = 22)
    private Double lat;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "RATING")
    private Integer rating;
    @Size(max = 125)
    @Column(name = "COMMENT", length = 125)
    private String comment;
    @JoinColumn(name = "DELIVEREDBY", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Deliveryboy deliveredby;
    @JoinColumn(name = "ORDEREDID", referencedColumnName = "ORDEREDID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ordered orderedid;

    public Delivery() {
    }

    public Delivery(Long deliveryid) {
        this.deliveryid = deliveryid;
    }

    public Long getDeliveryid() {
        return deliveryid;
    }

    public void setDeliveryid(Long deliveryid) {
        this.deliveryid = deliveryid;
    }

    public Date getWhenassigned() {
        return whenassigned;
    }

    public void setWhenassigned(Date whenassigned) {
        this.whenassigned = whenassigned;
    }

    public Date getWhendelivered() {
        return whendelivered;
    }

    public void setWhendelivered(Date whendelivered) {
        this.whendelivered = whendelivered;
    }

    public Double getLong1() {
        return long1;
    }

    public void setLong1(Double long1) {
        this.long1 = long1;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Deliveryboy getDeliveredby() {
        return deliveredby;
    }

    public void setDeliveredby(Deliveryboy deliveredby) {
        this.deliveredby = deliveredby;
    }

    public Ordered getOrderedid() {
        return orderedid;
    }

    public void setOrderedid(Ordered orderedid) {
        this.orderedid = orderedid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deliveryid != null ? deliveryid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Delivery)) {
            return false;
        }
        Delivery other = (Delivery) object;
        if ((this.deliveryid == null && other.deliveryid != null) || (this.deliveryid != null && !this.deliveryid.equals(other.deliveryid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.falisoft.entity.Delivery[ deliveryid=" + deliveryid + " ]";
    }

}
