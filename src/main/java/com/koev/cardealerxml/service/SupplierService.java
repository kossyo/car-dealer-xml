package com.koev.cardealerxml.service;


import javax.xml.bind.JAXBException;
import java.util.Set;

public interface SupplierService extends Seedable {
    void localSuppliers() throws JAXBException;
}
