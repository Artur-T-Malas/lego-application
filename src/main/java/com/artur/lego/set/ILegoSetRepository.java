package com.artur.lego.set;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILegoSetRepository extends JpaRepository<LegoSet, Long> {

    Optional<LegoSet> findByNumber(int number);
}
