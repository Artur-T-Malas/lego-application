package com.artur.lego.set;

public class LegoSetMapper {

    public static LegoSetDto mapDaoToDto(LegoSet legoSet) {
        return new LegoSetDto(
                legoSet.getNumber(),
                legoSet.getName(),
                legoSet.getNumberOfPieces(),
                legoSet.getCategoryId()
        );
    }

    public static LegoSet mapDtoToDao(LegoSetDto legoSetDto) {
        return new LegoSet(
                legoSetDto.getNumber(),
                legoSetDto.getName(),
                legoSetDto.getNumberOfPieces(),
                legoSetDto.getCategoryId()
        );
    }
}
