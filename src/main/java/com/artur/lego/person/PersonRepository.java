package com.artur.lego.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonRepository {

    private final IPersonRepository iPersonRepository;

    List<Person> findAll() {
        return iPersonRepository.findAll();
    }

    Optional<Person> findById(Long id) {
        return iPersonRepository.findById(id);
    }

    public void save(Person person) {
        iPersonRepository.save(person);
    }

    public void delete(Long id) {
        iPersonRepository.deleteById(id);
    }

}
