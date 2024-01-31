package com.artur.lego.set;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    void addLegoSet(@RequestBody @Valid LegoSetDto legoSetDto) {
        legoSetService.addLegoSetDto(legoSetDto);
    }

}
