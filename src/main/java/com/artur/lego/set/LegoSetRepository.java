package com.artur.lego.set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LegoSetRepository {

    private final ILegoSetRepository iLegoSetRepository;

    List<LegoSet> findAll() {
        return iLegoSetRepository.findAll();
    }

    LegoSet findByNumber(int number) {
        return iLegoSetRepository.findByNumber(number);
    }

}
