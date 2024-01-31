package com.artur.lego.set;

import com.artur.lego.category.Category;
import com.artur.lego.minifig.Minifig;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class LegoSetDto {

    @Min(value = 1, message = "Set number can't be blank!")
    private int number;

    @NotBlank(message = "Set name can't be blank!")
    private String name;

    private int numberOfPieces;
    private Long categoryId;

    public LegoSetDto(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public LegoSetDto(int number, String name, int numberOfPieces) {
        this.number = number;
        this.name = name;
        this.numberOfPieces = numberOfPieces;
    }

    public LegoSetDto(int number, String name, Long categoryId) {
        this.number = number;
        this.name = name;
        this.categoryId = categoryId;
    }
}
