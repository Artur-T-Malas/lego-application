package com.artur.lego.person;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    ResponseEntity<String> addPerson(@RequestBody @Valid PersonDto personDto) {
        personService.addPerson(personDto);
        return ResponseEntity.ok("Person added succesfully");
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok("Person deleted succesfully");
    }

}
