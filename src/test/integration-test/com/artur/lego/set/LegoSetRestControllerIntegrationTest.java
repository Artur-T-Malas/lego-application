package com.artur.lego.set;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class LegoSetRestControllerIntegrationTest {

    @MockBean
    private LegoSetRepository legoSetRepository;

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
        Mockito.when(legoSetRepository.findByNumber(999999999)).thenReturn(Optional.of(new LegoSet(
                Integer.parseInt(legoSetDto.getNumber()),
                legoSetDto.getName(),
                legoSetDto.getNumberOfPieces()
        )));
//        then
        System.out.println("-------------------------------");

        /*
        TODO
            Add an assertion to check that the returned by GET legoSetDto
            is the same one as we "sent" in the POST method.
         */

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/lego-sets/999999999"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers
                        .status().isOk()
                );
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

}
