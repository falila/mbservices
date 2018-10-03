package com.falisoft.common.dto;


import java.io.Serializable;

/**
 *
 * @author Raphael Keita keita.raphael@gmail.com
 */
public class Credential implements Serializable {

    private String username;
    private String password;

    public Credential() {
    }   

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
