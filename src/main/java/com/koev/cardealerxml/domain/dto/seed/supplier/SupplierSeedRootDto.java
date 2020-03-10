package com.koev.cardealerxml.domain.dto.seed.supplier;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedRootDto implements Dto {

    @XmlElement(name = "supplier")
    private Set<SupplierSeedDto> supplierSeedDtos;

    public SupplierSeedRootDto() {
    }

    public Set<SupplierSeedDto> getSupplierSeedDtos() {
        return supplierSeedDtos;
    }

    public void setSupplierSeedDtos(Set<SupplierSeedDto> supplierSeedDtos) {
        this.supplierSeedDtos = supplierSeedDtos;
    }
}
