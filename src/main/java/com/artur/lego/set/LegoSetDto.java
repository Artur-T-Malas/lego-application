package com.artur.lego.set;

import com.artur.lego.category.Category;
import com.artur.lego.minifig.Minifig;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LegoSetDto {

    private int number;
    private String name;
    private int numberOfPieces;
    private List<Minifig> minifigs;

    @ManyToOne()
    private Category category;
}
