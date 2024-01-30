package com.artur.lego.set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    LegoSetDto getSetByNumber(int number) {

        Optional<LegoSet> foundSet = legoSetRepository.findByNumber(number);

        if (foundSet.isEmpty()) {
            throw new LegoSetNotFoundException("There is no set with number " + number);
        }

        return LegoSetMapper.mapDaoToDto(foundSet.get());
    }

    public void addLegoSet(LegoSet legoSet) {
        legoSetRepository.save(legoSet);
    }

}
