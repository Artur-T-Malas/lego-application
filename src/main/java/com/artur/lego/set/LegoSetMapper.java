package com.artur.lego.set;

public class LegoSetMapper {

    public static LegoSetDto mapDaoToDto(LegoSet legoSet) {
        return new LegoSetDto(
                String.valueOf(legoSet.getNumber()),
                legoSet.getName(),
                legoSet.getNumberOfPieces(),
                legoSet.getCategory(),
                legoSet.getReleaseYear()
        );
    }

    public static LegoSet mapDtoToDao(LegoSetDto legoSetDto) {
        return new LegoSet(
                Integer.parseInt(legoSetDto.getNumber()),
                legoSetDto.getName(),
                legoSetDto.getNumberOfPieces(),
                legoSetDto.getCategory(),
                legoSetDto.getReleaseYear()
        );
    }
}
