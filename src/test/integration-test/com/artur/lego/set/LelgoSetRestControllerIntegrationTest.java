package com.artur.lego.set;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class LelgoSetRestControllerIntegrationTest {

    @MockBean
    private LegoSetRepository legoSetRepository;

    @Autowired
    LegoSetRestController legoSetRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestToLegoSetAndValidLegoSet_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String legoSetString = "{\n" +
                "    \"number\": \"10101\",\n" +
                "    \"name\": \"CCCCClone Trooper & Battle Droid Battle Pack\",\n" +
                "    \"numberOfPieces\": 215,\n" +
                "    \"categoryId\": null\n" +
                "}";

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
