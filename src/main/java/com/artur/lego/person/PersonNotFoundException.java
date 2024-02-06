package com.artur.lego.person;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String s) {
        super(s);
    }
}
