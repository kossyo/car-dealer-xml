package com.koev.cardealerxml.service;


import com.koev.cardealerxml.domain.dto._01_ordered_customers.CustomerRootQ1Dto;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Set;

public interface CustomerService extends Seedable {
    void getOrderedCustomers() throws JAXBException;

    void allCustomersWhoBoughtACar() throws JAXBException;
//
//    List<CustomerQuery5Dto> allCustomersWhoBoughtACar();
}
