package com.artur.lego.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    List<PersonDto> getAllPeople() {
        List<PersonDto> allPeople = new ArrayList<>();
        for (Person person : personRepository.findAll()) {
            allPeople.add(PersonMapper.mapDaoToDto(person));
        }
        return allPeople;
    }

    PersonDto getPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);

        if (person.isEmpty()) {
            throw new PersonNotFoundException("There is no person with an id " + id);
        }
        return PersonMapper.mapDaoToDto(person.get());
    }

}
