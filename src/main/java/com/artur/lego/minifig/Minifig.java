package com.artur.lego.minifig;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Minifig {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer numberOfPieces;

    public Minifig(String name, Integer numberOfPieces) {
        this.name = name;
        this.numberOfPieces = numberOfPieces;
    }
}
