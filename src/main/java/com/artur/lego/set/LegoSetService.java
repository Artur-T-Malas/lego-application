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
        return LegoSetMapper.mapDaoToDto(returnSetIfExists(number));
    }

    public void addLegoSet(LegoSet legoSet) {
        legoSetRepository.save(legoSet);
    }

    public void addLegoSetDto(LegoSetDto legoSetDto) {
        legoSetRepository.save(LegoSetMapper.mapDtoToDao(legoSetDto));
    }

    public void updateLegoSet(int number, LegoSetDto legoSetDto) {

        legoSetRepository.save(new LegoSet(
                getSetIdFromNumber(number),
                Integer.parseInt(legoSetDto.getNumber()), // new set number
                legoSetDto.getName(),
                legoSetDto.getNumberOfPieces(),
                legoSetDto.getCategoryId()
        ));
    }

    LegoSet returnSetIfExists (int number) {
        Optional<LegoSet> foundSet = legoSetRepository.findByNumber(number);

        if (foundSet.isEmpty()) {
            throw new LegoSetNotFoundException("There is no set with number " + number);
        }

        return foundSet.get();
    }

    Long getCorrespondingIdFromDto (LegoSetDto legoSetDto) {

        return returnSetIfExists(Integer.parseInt(legoSetDto.getNumber())).getId();

    }

    Long getSetIdFromNumber (int number) {
        return returnSetIfExists(number).getId();
    }
}
