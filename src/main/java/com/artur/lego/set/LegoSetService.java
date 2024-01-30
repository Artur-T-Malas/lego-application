package com.artur.lego.set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LegoSetService {

    private final LegoSetRepository legoSetRepository;

    List<LegoSetDto> getAllLegoSets() {
        return legoSetRepository
                .findAll()
                .stream()
                .map(LegoSetMapper::mapDaoToDto)
                .toList();
    }

    public LegoSet getSetByNumber(int number) {
        return legoSetRepository.findByNumber(number);
    }
}
