package com.falisoft.exception;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2018)
 * @author Raphael KEITA
 * 
 */
public class OrderCreationException extends Exception  {

    public OrderCreationException(String message) {
        super(message);
    }

    
      public OrderCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
