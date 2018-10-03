package com.falisoft.common.dto;


import java.io.Serializable;

/**
 *
 * @author Raphael Keita keita.raphael@gmail.com
 */
public class ResourceStatus implements Serializable{
    private int code;
    private String message;
    private Object data;

    public ResourceStatus() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
}
