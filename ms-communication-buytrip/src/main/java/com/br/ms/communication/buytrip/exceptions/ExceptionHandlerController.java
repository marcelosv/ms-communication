package com.br.ms.communication.buytrip.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String process(MethodArgumentNotValidException ex) {
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for( ObjectError item : ex.getBindingResult().getAllErrors() ){
    		sb.append(item.getDefaultMessage());
    		sb.append("\n");
    	}
    	
        return sb.toString();
    }
    
}
