package com.koev.cardealerxml.repository;

import com.koev.cardealerxml.domain.entity.Car;
import com.koev.cardealerxml.domain.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PartRepository extends JpaRepository<Part, Integer> {


    @Query("select p from Part p where :car in (p.cars)")
    Set<Part> findAllOfGivenCar(Car car);
}
