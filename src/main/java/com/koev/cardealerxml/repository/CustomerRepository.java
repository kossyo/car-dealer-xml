package com.koev.cardealerxml.repository;

import com.koev.cardealerxml.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer c order by c.birthDate, c.isYoungDriver")
    Set<Customer> orderedCustomers();

    @Query("select c from Customer c where size(c.sales) > 0")
    Set<Customer> findAllWhoHaveBoughtAtLeast1Car();


}
