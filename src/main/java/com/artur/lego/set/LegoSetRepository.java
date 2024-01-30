package com.artur.lego.set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LegoSetRepository {

    private final ILegoSetRepository iLegoSetRepository;

    List<LegoSet> findAll() {
        return iLegoSetRepository.findAll();
    }

    Optional<LegoSet> findByNumber(int number) {
        return iLegoSetRepository.findByNumber(number);
    }

    void save(LegoSet legoSet) {
        iLegoSetRepository.save(legoSet);
    }

}
