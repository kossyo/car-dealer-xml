package com.koev.cardealerxml.domain.dto.seed.car;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedRootDto implements Dto {

    @XmlElement(name = "car")
    private Set<CarSeedDto> carSeedDto;

    public CarSeedRootDto() {
    }

    public Set<CarSeedDto> getCarSeedDto() {
        return carSeedDto;
    }

    public void setCarSeedDto(Set<CarSeedDto> carSeedDto) {
        this.carSeedDto = carSeedDto;
    }
}
