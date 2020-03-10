package com.koev.cardealerxml.domain.dto._03_local_suppliers;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierQ3Dto implements Dto {

    @XmlAttribute
    private Integer id;

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "parts-count")
    private Integer partsCount;

    public SupplierQ3Dto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(Integer partsCount) {
        this.partsCount = partsCount;
    }
}
