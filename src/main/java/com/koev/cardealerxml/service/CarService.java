package com.koev.cardealerxml.service;


import javax.xml.bind.JAXBException;
import java.util.Set;

public interface CarService extends Seedable {
    void carsFromMakeToyota() throws JAXBException;
//
    void findAllCarsQuery4() throws JAXBException;
}
