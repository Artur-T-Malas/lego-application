package com.artur.lego.set;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@TestPropertySource(locations = "classpath:application-test.properties")
public class LegoSetRestControllerIntegrationTest {


    @Autowired
    LegoSetRestController legoSetRestController;

    @Autowired
    LegoSetService legoSetService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldReturnListOfAllLegoSets() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/lego-sets"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnLegoSetWithGivenIdIfExists() throws Exception {
//        given
        ObjectMapper objectMapper = new ObjectMapper();
        LegoSetDto legoSetDto = new LegoSetDto(
                "999999999",
                "Test Set",
                1111
        );

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/lego-sets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(legoSetDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .status().isOk()
                );

//        when

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/lego-sets/999999999"))
                //        then
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .status().isOk()
                )
                .andExpect(MockMvcResultMatchers
                        .content().json(objectMapper.writeValueAsString(legoSetDto))
                );

//        after, clean database from what was added
        legoSetService.deleteLegoSet(Integer.parseInt(legoSetDto.getNumber()));
    }

    @Test
    public void whenPostRequestToLegoSetAndValidLegoSet_thenCorrectResponse() throws Exception {
//        given
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String legoSetString = "{\n" +
                "    \"number\": \"10101\",\n" +
                "    \"name\": \"CCCCClone Trooper & Battle Droid Battle Pack\",\n" +
                "    \"numberOfPieces\": 215,\n" +
                "    \"categoryId\": null\n" +
                "}";
//        when
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/lego-sets")
                .content(legoSetString)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8));

//        after, clean the database from what was added
        legoSetService.deleteLegoSet(10101);
    }


    @Test
    public void whenPostRequestToLegoSetAndInvalidLegoSet_thenCorrectResponse() throws Exception {
        String legoSetString = "{\n" +
                "    \"number\": \"\",\n" +
                "    \"name\": \"CCCCClone Trooper & Battle Droid Battle Pack\",\n" +
                "    \"numberOfPieces\": 215,\n" +
                "    \"categoryId\": null\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/lego-sets")
                .content(legoSetString)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldUpdateExistingLegoSet() throws Exception {
//        given
        LegoSet legoSet = new LegoSet(
                1000L,
                1000,
                "Set",
                1000,
                null,
                2022
                );

        LegoSet updatedLegoSet = new LegoSet( // expected result
                1000L,
                999,
                "Updated Set",
                999,
                null,
                2022
        );

        LegoSetDto updatingLegoSetDto = new LegoSetDto( // DTO to be sent to PUT method
                "999",
                "Updated Set",
                999
        );

        LegoSetDto updatedLegoSetDto = updatingLegoSetDto;

        ObjectMapper objectMapper = new ObjectMapper();

        legoSetService.addLegoSet(legoSet);

//        when

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/lego-sets/" + legoSet.getNumber())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatingLegoSetDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

//        then
//        check that API returns the updated DTO
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/lego-sets/" + updatedLegoSet.getNumber()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content().string(objectMapper.writeValueAsString(updatedLegoSetDto)));

//        and check that there is nothing at the "old" number
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/lego-sets/" + legoSet.getNumber()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .status().isNotFound());

//        after, clean the database from was added
        legoSetService.deleteLegoSet(updatedLegoSet.getNumber());
    }

    @Test
    public void shouldDeleteLegoSet() throws Exception {
//        given
        LegoSetDto legoSetDto = new LegoSetDto(
                "1000",
                "Set",
                1000,
                null
        );

        legoSetService.addLegoSetDto(legoSetDto);

        ObjectMapper objectMapper = new ObjectMapper();

//        check if the set was added succesfully
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/lego-sets/" + legoSetDto.getNumber()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content().string(objectMapper.writeValueAsString(legoSetDto)));

//        when
//        send a DELETE request
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/lego-sets/" + legoSetDto.getNumber()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .status().isOk());

//        then
//        check if the set can be no longer found
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/lego-sets/" + legoSetDto.getNumber()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .status().isNotFound());
    }

}
