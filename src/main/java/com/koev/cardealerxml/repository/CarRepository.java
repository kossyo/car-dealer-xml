package com.koev.cardealerxml.repository;

import com.koev.cardealerxml.domain.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Set<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);
}
