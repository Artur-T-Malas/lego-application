package com.artur.lego.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Locale;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")
public class PersonRestControllerIntegrationTest {

    @Autowired
    PersonRestController personRestController;

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldGetAllPeople() throws Exception {
//        given
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person(
                2L,
                "Test123",
                "test@test.pl",
                null,
                null,
                null
        );
        Person person2 = new Person(
                3L,
                "Test234",
                "test2@test.pl",
                null,
                null,
                null
        );
        PersonDto expectedPersonDto = PersonMapper.mapDaoToDto(person);
        PersonDto expectedPersonDto2 = PersonMapper.mapDaoToDto(person2);

        personRepository.save(person);
        personRepository.save(person2);

//        when

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/people"))
                .andDo(MockMvcResultHandlers.print())
//                then
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content().string(objectMapper
                                .writeValueAsString(
                                        List.of(expectedPersonDto, expectedPersonDto2)
                                )));
//        after
        personRepository.delete(person.getId());
        personRepository.delete(person2.getId());
    }

    @Test
    public void shouldGetPersonWithGivenIdIfExists() throws Exception {
//        given
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person(
                1L,
                "Test123",
                "test@test.pl",
                null,
                null,
                null
        );
        PersonDto expectedPersonDto = PersonMapper.mapDaoToDto(person);

        personRepository.save(person);

//        when
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/people/" + person.getId()))
                .andDo(MockMvcResultHandlers.print())
//                then
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content().json(objectMapper.writeValueAsString(expectedPersonDto)));
//        after
        personRepository.delete(person.getId());

    }

}
