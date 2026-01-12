// Class to handle exceptions globally
package com.group3.digitalWallet.exceptions;
import com.group3.digitalWallet.InsufficientFundsException;
import com.group3.digitalWallet.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody String handleUserNotFoundException(UserNotFoundException ex) {
        return ex.getMessage();
    }


    @ExceptionHandler(value = InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInsufficientFundsException(InsufficientFundsException ex) {
        return ex.getMessage();
    }



}
