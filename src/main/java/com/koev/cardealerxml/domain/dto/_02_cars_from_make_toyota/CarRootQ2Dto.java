package com.koev.cardealerxml.domain.dto._02_cars_from_make_toyota;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarRootQ2Dto implements Dto {

    @XmlElement(name = "car")
    private Set<CarQ2Dto> carQ2Dtos;

    public CarRootQ2Dto() {
    }

    public Set<CarQ2Dto> getCarQ2Dtos() {
        return carQ2Dtos;
    }

    public void setCarQ2Dtos(Set<CarQ2Dto> carQ2Dtos) {
        this.carQ2Dtos = carQ2Dtos;
    }
}
