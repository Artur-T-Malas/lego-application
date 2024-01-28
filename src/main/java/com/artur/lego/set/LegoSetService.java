package com.artur.lego.set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LegoSetService {

    private final LegoSetRepository legoSetRepository;

    List<LegoSet> getAllLegoSets() {
        return legoSetRepository.findAll();
    }

}
