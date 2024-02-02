package com.artur.lego.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {

    @NotBlank(message = "Category name can't be blank")
    @Pattern(regexp = "[a-zA-Z ]+", message = "Category name must be only letters")
    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }
}
