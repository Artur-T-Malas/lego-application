package com.artur.lego.set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LegoSetServiceTest {

    @Mock
    LegoSetRepository legoSetRepository;

    @InjectMocks
    LegoSetService legoSetService;

    @Test
    public void shouldReturnListOfLegoSetDto() {

//        given
        LegoSet set1 = new LegoSet(
                111,
                "SetOne",
                101,
                List.of(),
                1L
        );
        LegoSet set2 = new LegoSet(
                222,
                "SetTwo",
                202,
                List.of(),
                2L
        );

        LegoSetDto setDto1 = new LegoSetDto(
                111,
                "SetOne",
                101,
                List.of(),
                1L
        );
        LegoSetDto setDto2 = new LegoSetDto(
                222,
                "SetTwo",
                202,
                List.of(),
                2L
        );

//        when
        Mockito.when(legoSetRepository.findAll()).thenReturn(List.of(set1, set2));

//        then
        assertEquals(List.of(setDto1, setDto2), legoSetService.getAllLegoSets());
    }
}