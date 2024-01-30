package com.artur.lego;

import com.artur.lego.set.LegoSetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LegoSetNotFoundException.class)
    String legoSetNotFoundExceptionHandler(LegoSetNotFoundException exception) {
        return exception.getMessage();
    }

}
