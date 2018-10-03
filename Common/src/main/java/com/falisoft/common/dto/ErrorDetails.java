/*
 * __NAME__.java
 *
 * Created on __DATE__, __TIME__
 *
 * Copyright(c) {2017} Falisoft, Inc.  All Rights Reserved.
 * This software is the proprietary information of Falisoft.
 *
 */
package com.falisoft.common.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (09 2018)
 * @author Raphael KEITA
 *
 */
public class ErrorDetails implements Serializable{

    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
         super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
