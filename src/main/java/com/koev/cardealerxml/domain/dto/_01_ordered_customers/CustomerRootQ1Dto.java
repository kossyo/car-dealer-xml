package com.koev.cardealerxml.domain.dto._01_ordered_customers;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerRootQ1Dto implements Dto {

    @XmlElement(name = "customer")
    Set<CustomerQ1Dto> customerQ1Dtos;

    public CustomerRootQ1Dto() {
    }

    public Set<CustomerQ1Dto> getCustomerQ1Dtos() {
        return customerQ1Dtos;
    }

    public void setCustomerQ1Dtos(Set<CustomerQ1Dto> customerQ1Dtos) {
        this.customerQ1Dtos = customerQ1Dtos;
    }
}
