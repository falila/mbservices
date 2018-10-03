package com.falisoft.api.controler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * {Insert class description here}
 *
 * @version $Revision: 1.0 $
 * @since Build {1.0} (11 2018)
 * @author Raphael KEITA
 * 
 */
@ControllerAdvice
public class ExceptionHandlerController {

@ExceptionHandler(InvalidFormatException.class)
    @ResponseBody public String typeMismatchException(HttpServletRequest request, HttpServletResponse servletResponse, InvalidFormatException e ) {
        String yourResponse = e.getMessage();
        return yourResponse;
    }

}