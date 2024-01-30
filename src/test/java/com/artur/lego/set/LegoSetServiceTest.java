package com.artur.lego.set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class LegoSetServiceTest {

    @Mock
    LegoSetRepository legoSetRepository;

    @InjectMocks
    LegoSetService legoSetService;

    @BeforeEach
    void prepareLegoSetsData() {

    }

    @Test
    public void shouldReturnListOfLegoSetDto() {

//        given
        LegoSet set1 = new LegoSet(
                111,
                "SetOne",
                101,
                1L
        );
        LegoSet set2 = new LegoSet(
                222,
                "SetTwo",
                202,
                2L
        );

        LegoSetDto setDto1 = new LegoSetDto(
                111,
                "SetOne",
                101,
                1L
        );
        LegoSetDto setDto2 = new LegoSetDto(
                222,
                "SetTwo",
                202,
                2L
        );

//        when
        Mockito.when(legoSetRepository.findAll()).thenReturn(List.of(set1, set2));

//        then
        assertEquals(List.of(setDto1, setDto2), legoSetService.getAllLegoSets());
    }

    @Test
    public void shouldReturnSetByNumber() {
        //        given
        LegoSet set = new LegoSet(
                222,
                "SetTwo",
                202,
                2L
        );
        LegoSetDto setDto = new LegoSetDto(
                222,
                "SetTwo",
                202,
                2L
        );

//        when
        Mockito.when(legoSetRepository.findByNumber(222)).thenReturn(Optional.of(set));

//        then
        assertEquals(setDto, legoSetService.getSetByNumber(222));
    }

    @Test
    public void shouldThrowExceptionWhenNoSetFoundWithNumber() {
//        when
        Mockito.when(legoSetRepository.findByNumber(any(Integer.class))).thenThrow(new LegoSetNotFoundException("message"));

//        then
        assertThrows(LegoSetNotFoundException.class,
                () -> legoSetService.getSetByNumber(any(Integer.class)));
    }
}