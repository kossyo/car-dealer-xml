package com.koev.cardealerxml.domain.dto.seed.part;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartSeedRootDto implements Dto {

    @XmlElement(name = "part")
    private Set<PartSeedDto> partSeedDtos;

    public PartSeedRootDto() {
    }

    public Set<PartSeedDto> getPartSeedDtos() {
        return partSeedDtos;
    }

    public void setPartSeedDtos(Set<PartSeedDto> partSeedDtos) {
        this.partSeedDtos = partSeedDtos;
    }
}
