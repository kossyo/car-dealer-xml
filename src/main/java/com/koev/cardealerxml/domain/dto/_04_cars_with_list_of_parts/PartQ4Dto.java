package com.koev.cardealerxml.domain.dto._04_cars_with_list_of_parts;

import com.koev.cardealerxml.domain.dto.Dto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartQ4Dto implements Dto {
    
    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;

    public PartQ4Dto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
