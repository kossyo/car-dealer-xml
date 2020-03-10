package com.koev.cardealerxml.domain.dto._06_sales_with_applied_discount;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleQ6RootDto implements Dto {

    @XmlElement(name = "sale")
    private Set<SaleQ6Dto> saleQ6Dto;

    public SaleQ6RootDto() {

    }

    public Set<SaleQ6Dto> getSaleQ6Dto() {
        return saleQ6Dto;
    }

    public void setSaleQ6Dto(Set<SaleQ6Dto> saleQ6Dto) {
        this.saleQ6Dto = saleQ6Dto;
    }
}
