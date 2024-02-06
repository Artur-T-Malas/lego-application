package com.artur.lego.person;

public class PersonMapper {

    static PersonDto mapDaoToDto(Person person) {
        return new PersonDto(
                person.getNickname(),
                person.getEmail(),
                person.getFirstName(),
                person.getLastName(),
                person.getPhoneNumber()
        );
    }

}
