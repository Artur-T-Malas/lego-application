package com.artur.lego.minifig;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Minifig {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "orders_generator", sequenceName = "orders_seq", allocationSize = 1)
    private Long id;

    private String name;

    private int numberOfPieces;
}
