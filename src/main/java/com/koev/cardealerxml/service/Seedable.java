package com.koev.cardealerxml.service;


import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.JAXBException;

public interface Seedable {

    void seed() throws JAXBException;

    boolean handleViolations(Dto dto);
}
