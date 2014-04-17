package com.rdfgroup.cms.controller.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.httpclient.auth.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.rdfgroup.cms.services.exception.JsonError;

public class BaseRestController {

	private static final Logger log = Logger.getLogger(BaseRestController.class.getName());
	
	@ExceptionHandler (Exception.class)
    @ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleAllExceptions(Exception ex) {
	    log.log(Level.SEVERE, "Exception in RestController", ex);
        return new JsonError(ex.getMessage()).asModelAndView();
    }   
	
    @ExceptionHandler (AuthenticationException.class)
    @ResponseStatus (HttpStatus.UNAUTHORIZED)
    public ModelAndView handleLoginExceptions(Exception ex) {
        log.log(Level.WARNING, "Login error", ex);
        return new JsonError(ex.getMessage()).asModelAndView();
    }
	
}
