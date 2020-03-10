package com.koev.cardealerxml.domain.dto.seed.customer;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerRootSeedDto implements Dto {

    @XmlElement(name = "customer")
    private Set<CustomerSeedDto> customerSeedDtos;

    public CustomerRootSeedDto() {
    }

    public Set<CustomerSeedDto> getCustomerSeedDtos() {
        return customerSeedDtos;
    }

    public void setCustomerSeedDtos(Set<CustomerSeedDto> customerSeedDtos) {
        this.customerSeedDtos = customerSeedDtos;
    }
}
