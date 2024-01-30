package com.artur.lego.set;

import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lego-sets")
public class LegoSetRestController {

    private final LegoSetService legoSetService;

    @GetMapping
    List<LegoSetDto> getAllLegoSets() {
        return legoSetService.getAllLegoSets();
    }

    @GetMapping("/{number}")
    LegoSetDto getLegoSetByNumber(@PathVariable int number) {
        return legoSetService.getSetByNumber(number);
    }

}
