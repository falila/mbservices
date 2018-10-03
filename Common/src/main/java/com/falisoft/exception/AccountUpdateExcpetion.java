
package com.falisoft.exception;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2017)
 * @author Raphael KEITA
 * 
 */
public class AccountUpdateExcpetion extends Exception {

    public AccountUpdateExcpetion(String message, Exception ex) {
    }

    public AccountUpdateExcpetion(String message) {
        super(message);
    }

    public AccountUpdateExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountUpdateExcpetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    
}
