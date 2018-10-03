package com.falisoft.common.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (09 2018)
 * @author Raphael KEITA
 *
 */
public class AccountRequest implements Serializable {

    @NotNull(message = "username should not be  blank") 
    private String username;
    @NotNull(message = "firstname should not be  blank") 
    private String firstname;
    @NotNull(message = "lastname should not be  blank") 
    private String lastname;
    @NotNull(message = "password should not be  blank") 
    private String password;
    @NotNull(message = "email should not be  blank") 
    private String email;
    private String reference;
    @NotNull(message = "driver licenc enumber should not be  blank") 
    private String phone;
    @NotNull(message = "type should not be  blank") 
    private String type;
    @NotNull(message = "address should not be  empty") 
    private String address;
    

    public AccountRequest(String username, String firstname, String lastname, String password, String email, String reference, String phone, String type,String addres) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.reference = reference;
        this.phone = phone;
        this.type = type;
        this.address = addres;
    }

    public AccountRequest(String username, String password, String type, String address) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.address = address;
    }

    public AccountRequest() {
    }   
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }    
}
