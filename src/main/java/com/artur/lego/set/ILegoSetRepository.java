package com.artur.lego.set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ILegoSetRepository extends JpaRepository<LegoSet, Long> {

    LegoSet findByNumber(int number);

}
