package com.artur.lego.set;

import com.artur.lego.category.Category;
import com.artur.lego.minifig.Minifig;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class LegoSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "orders_generator", sequenceName = "orders_seq", allocationSize = 1)
    private Long id;

    private int number;
    private String name;
    private int numberOfPieces;
    private List<Minifig> minifigs;

    @ManyToOne()
    private Category category;

    public LegoSet(int number, String name, int numberOfPieces, List<Minifig> minifigs, Category category) {
        this.number = number;
        this.name = name;
        this.numberOfPieces = numberOfPieces;
        this.minifigs = minifigs;
        this.category = category;
    }
}
