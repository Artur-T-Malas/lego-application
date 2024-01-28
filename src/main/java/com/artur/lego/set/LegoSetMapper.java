package com.artur.lego.set;

public class LegoSetMapper {

    public static LegoSetDto mapDaoToDto(LegoSet legoSet) {
        return new LegoSetDto(
                legoSet.getNumber(),
                legoSet.getName(),
                legoSet.getNumberOfPieces(),
                legoSet.getMinifigs(),
                legoSet.getCategoryId()
        );
    }

}
