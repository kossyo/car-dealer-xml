package com.koev.cardealerxml.domain.dto._05_total_sales_by_customer;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerQ5RootDto implements Dto {

    @XmlElement(name = "customer")
    private List<CustomerQ5Dto> customerQ5Dtos;

    public CustomerQ5RootDto() {
    }

    public List<CustomerQ5Dto> getCustomerQ5Dtos() {
        return customerQ5Dtos;
    }

    public void setCustomerQ5Dtos(List<CustomerQ5Dto> customerQ5Dtos) {
        this.customerQ5Dtos = customerQ5Dtos;
    }
}
