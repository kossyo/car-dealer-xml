package com.koev.cardealerxml.service;


import javax.xml.bind.JAXBException;
import java.util.Set;

public interface SaleService  {
    void createSalesRelations();

    void salesWithAppliedDiscount() throws JAXBException;
//
//    Set<SaleQuery6Dto> getAll();
}
