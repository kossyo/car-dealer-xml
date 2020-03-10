package com.koev.cardealerxml.domain.dto._03_local_suppliers;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierQ3RootDto implements Dto {

    @XmlElement(name = "supplier")
    private Set<SupplierQ3Dto> supplierQ3Dtos;

    public SupplierQ3RootDto() {
    }

    public Set<SupplierQ3Dto> getSupplierQ3Dtos() {
        return supplierQ3Dtos;
    }

    public void setSupplierQ3Dtos(Set<SupplierQ3Dto> supplierQ3Dtos) {
        this.supplierQ3Dtos = supplierQ3Dtos;
    }
}
