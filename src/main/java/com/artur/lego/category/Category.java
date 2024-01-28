package com.artur.lego.category;

import com.artur.lego.set.LegoSet;
import jakarta.persistence.*;

import java.util.Set;

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "orders_generator", sequenceName = "orders_seq", allocationSize = 1)
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "CATEGORY_ID")
    private Set<LegoSet> legoSets;
}
