package com.artur.lego;

import com.artur.lego.set.LegoSetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LegoSetNotFoundException.class)
    String legoSetNotFoundExceptionHandler(LegoSetNotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    String httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    List<String> bindExceptionHandler(BindException bindException) {

        List<FieldError> fieldErrors = bindException.getBindingResult().getFieldErrors();
        List<String> stringList = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            stringList.add(fieldError.getDefaultMessage());
        }

        return stringList;
    }

}
