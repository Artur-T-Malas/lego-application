package com.artur.lego.set;

import com.artur.lego.category.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class LegoSetDto {

    @NotBlank(message = "Set number can't be blank!")
    @Pattern(regexp = "[0-9]+", message = "Set number can only contain digits!")
    private String number;

    @NotBlank(message = "Set name can't be blank!")
    private String name;

    private int numberOfPieces;
    private Category category;
    private int releaseYear;

    public LegoSetDto(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public LegoSetDto(String number, String name, int numberOfPieces) {
        this.number = number;
        this.name = name;
        this.numberOfPieces = numberOfPieces;
    }

    public LegoSetDto(String number, String name, Category category) {
        this.number = number;
        this.name = name;
        this.category = category;
    }

    public LegoSetDto(String number, String name, int numberOfPieces, Category category) {
        this.number = number;
        this.name = name;
        this.numberOfPieces = numberOfPieces;
        this.category = category;
    }
}
