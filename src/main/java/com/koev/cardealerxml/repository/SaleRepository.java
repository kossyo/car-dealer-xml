package com.koev.cardealerxml.repository;

import com.koev.cardealerxml.domain.entity.Customer;
import com.koev.cardealerxml.domain.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    Set<Sale> findAllByCustomer(Customer customer);
}
