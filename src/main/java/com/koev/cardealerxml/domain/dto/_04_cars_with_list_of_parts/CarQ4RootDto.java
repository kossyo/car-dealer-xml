package com.koev.cardealerxml.domain.dto._04_cars_with_list_of_parts;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarQ4RootDto implements Dto {

    @XmlElement(name = "car")
    private Set<CarQ4Dto> carQ4Dtos;

    public CarQ4RootDto() {
    }

    public Set<CarQ4Dto> getCarQ4Dtos() {
        return carQ4Dtos;
    }

    public void setCarQ4Dtos(Set<CarQ4Dto> carQ4Dtos) {
        this.carQ4Dtos = carQ4Dtos;
    }
}
