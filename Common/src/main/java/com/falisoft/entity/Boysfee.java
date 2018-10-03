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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Table(name = "boysfee")
@NamedQueries({
    @NamedQuery(name = "Boysfee.findAll", query = "SELECT b FROM Boysfee b")})
public class Boysfee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BOYSFEE", nullable = false)
    private Long boysfee;
    @Column(name = "CODE")
    private Integer code;
    @Size(max = 45)
    @Column(name = "DESCRIPTION", length = 45)
    private String description;
    @Column(name = "VALUE1")
    private Long value1;
    @Column(name = "VALUE2")
    private Long value2;
    @Column(name = "VALUE3")
    private Long value3;

    public Boysfee() {
    }

    public Boysfee(Long boysfee) {
        this.boysfee = boysfee;
    }

    public Long getBoysfee() {
        return boysfee;
    }

    public void setBoysfee(Long boysfee) {
        this.boysfee = boysfee;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getValue1() {
        return value1;
    }

    public void setValue1(Long value1) {
        this.value1 = value1;
    }

    public Long getValue2() {
        return value2;
    }

    public void setValue2(Long value2) {
        this.value2 = value2;
    }

    public Long getValue3() {
        return value3;
    }

    public void setValue3(Long value3) {
        this.value3 = value3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (boysfee != null ? boysfee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boysfee)) {
            return false;
        }
        Boysfee other = (Boysfee) object;
        if ((this.boysfee == null && other.boysfee != null) || (this.boysfee != null && !this.boysfee.equals(other.boysfee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.falisoft.entity.Boysfee[ boysfee=" + boysfee + " ]";
    }

}
