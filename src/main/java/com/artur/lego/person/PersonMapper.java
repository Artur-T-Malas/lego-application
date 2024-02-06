package com.artur.lego.person;

public class PersonMapper {

    static Person mapDtoToDao(PersonDto personDto) {
        return new Person(
                personDto.getNickname(),
                personDto.getEmail(),
                personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getPhoneNumber()
        );
    }

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
