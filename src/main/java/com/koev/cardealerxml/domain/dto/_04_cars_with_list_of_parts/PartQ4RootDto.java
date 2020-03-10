package com.koev.cardealerxml.domain.dto._04_cars_with_list_of_parts;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartQ4RootDto implements Dto {

    @XmlElement(name = "part")
    private Set<PartQ4Dto> partQ4Dtos;

    public PartQ4RootDto() {
    }

    public Set<PartQ4Dto> getPartQ4Dtos() {
        return partQ4Dtos;
    }

    public void setPartQ4Dtos(Set<PartQ4Dto> partQ4Dtos) {
        this.partQ4Dtos = partQ4Dtos;
    }
}
