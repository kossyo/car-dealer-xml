package com.koev.cardealerxml.domain.dto._04_cars_with_list_of_parts;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarQ4Dto implements Dto {

    @XmlAttribute
    private String make;

    @XmlAttribute
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private String travelledDistance;
    
    @XmlElement(name = "parts")
    private PartQ4RootDto partQ4RootDto;

    public CarQ4Dto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public PartQ4RootDto getPartQ4RootDto() {
        return partQ4RootDto;
    }

    public void setPartQ4RootDto(PartQ4RootDto partQ4RootDto) {
        this.partQ4RootDto = partQ4RootDto;
    }
}
