/*
 * Copyright(c) {2017} Falisoft, Inc.  All Rights Reserved.
 * This software is the proprietary information of Falisoft.
 *
 */
package com.falisoft.common.dto;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0
 * @since Build {1.0} (2018)
 * @author Raphael KEITA
 *
 */
public enum UserTypeEnum {
    A, B, C, D, U, M;
    
    private String code;

    private UserTypeEnum() {
    }

    private UserTypeEnum(String code) {
        this.code = code;
    }
    
    
}
