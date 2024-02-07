package com.artur.lego.category;

import com.artur.lego.set.LegoSet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Category name can't be blank")
    @Pattern(regexp = "[a-zA-Z ]+", message = "Category name must be only letters")
    @Column(unique = true)
    private String name;

    public Category(String name) {
        this.name = name;
    }

}
