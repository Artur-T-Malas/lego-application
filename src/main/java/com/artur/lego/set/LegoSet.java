package com.artur.lego.set;

import com.artur.lego.category.Category;
import com.artur.lego.minifig.Minifig;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegoSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private int number;

    private String name;
    private int numberOfPieces;

    private Long categoryId;

    public LegoSet(int number, String name, int numberOfPieces,Long categoryId) {
        this.number = number;
        this.name = name;
        this.numberOfPieces = numberOfPieces;
        this.categoryId = categoryId;
    }

    public LegoSet(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public LegoSet(int number, String name, int numberOfPieces) {
        this.number = number;
        this.name = name;
        this.numberOfPieces = numberOfPieces;
    }

    public LegoSet(int number, String name, Long categoryId) {
        this.number = number;
        this.name = name;
        this.categoryId = categoryId;
    }
}
