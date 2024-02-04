package com.artur.lego.set;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<String> addLegoSet (@RequestBody @Valid LegoSetDto legoSetDto) {
        legoSetService.addLegoSetDto(legoSetDto);
        return ResponseEntity.ok("Data is valid");
    }

    @PutMapping("/{number}")
    ResponseEntity<String> updateLegoSet(@PathVariable int number, @RequestBody @Valid LegoSetDto legoSetDto) {
        legoSetService.updateLegoSet(number, legoSetDto);
        return ResponseEntity.ok("Update successful");
    }

}
