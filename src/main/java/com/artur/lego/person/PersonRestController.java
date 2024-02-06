package com.artur.lego.person;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
public class PersonRestController {

    private final PersonService personService;

    @GetMapping
    List<PersonDto> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping("/{id}")
    PersonDto getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

}
