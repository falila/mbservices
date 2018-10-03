/*
 * Copyright(c) {2017} Falisoft, Inc.  All Rights Reserved.
 * This software is the proprietary information of Falisoft.
 *
 */
package com.falisoft.common.dto;

import com.falisoft.entity.User;
import java.io.Serializable;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0
 * @since Build {1.0} (2018)
 * @author Raphael KEITA
 *
 */
public class AccountDataDTO implements Serializable {

    private Long id;
    private String userName;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String reference;
    private String address;
    private String type;
    private Integer level;
    private Integer rating;
    private Integer status;
    private String driverlicencenumber;
    private String engineplatenumber;
    private String enginemodel;
    private String enginetype;
    private String licenceexpdate;
    private String locationcode;

    public AccountDataDTO() {
    }

    public AccountDataDTO(User user) {
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.reference = user.getReference();
        this.userName = user.getUsername();
        this.id = user.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getLicenceexpdate() {
        return licenceexpdate;
    }

    public void setLicenceexpdate(String licenceexpdate) {
        this.licenceexpdate = licenceexpdate;
    }

    public String getLocationcode() {
        return locationcode;
    }

    public void setLocationcode(String locationcode) {
        this.locationcode = locationcode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
