package com.koev.cardealerxml.service.impl;

import com.koev.cardealerxml.constants.Constants;
import com.koev.cardealerxml.domain.dto.Dto;
import com.koev.cardealerxml.domain.dto._01_ordered_customers.CustomerQ1Dto;
import com.koev.cardealerxml.domain.dto._01_ordered_customers.CustomerRootQ1Dto;
import com.koev.cardealerxml.domain.dto._05_total_sales_by_customer.CustomerQ5Dto;
import com.koev.cardealerxml.domain.dto._05_total_sales_by_customer.CustomerQ5RootDto;
import com.koev.cardealerxml.domain.dto.seed.customer.CustomerRootSeedDto;
import com.koev.cardealerxml.domain.dto.seed.customer.CustomerSeedDto;
import com.koev.cardealerxml.domain.entity.Customer;
import com.koev.cardealerxml.domain.entity.Part;
import com.koev.cardealerxml.domain.entity.Sale;
import com.koev.cardealerxml.repository.CustomerRepository;
import com.koev.cardealerxml.repository.SaleRepository;
import com.koev.cardealerxml.service.CustomerService;
import com.koev.cardealerxml.service.SeedableService;
import com.koev.cardealerxml.util.ValidatorUtil;
import com.koev.cardealerxml.util.tobedeleted.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.koev.cardealerxml.constants.Constants.OUTPUT_FILE_PATH_TOTAL_SALES_BY_CUSTOMER;

@Service
public class CustomerServiceImpl extends SeedableService implements CustomerService {

    private static final String XML_INPUT_CUSTOMERS = Constants.XML_INPUT_CUSTOMERS;
    private static final String OUTPUT_FILE_PATH_ORDERED_CUSTOMERS = Constants.OUTPUT_FILE_PATH_ORDERED_CUSTOMERS;

    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    protected CustomerServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, Random random,
                                  CustomerRepository customerRepository, SaleRepository saleRepository,
                                  XmlParser xmlParser
    ) {
        super(modelMapper, random, validatorUtil, xmlParser);
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void seed() throws JAXBException {

        CustomerRootSeedDto customerRootSeedDto = super.getXmlParser().parseXml(CustomerRootSeedDto.class, XML_INPUT_CUSTOMERS);
        for (CustomerSeedDto customerSeedDto : customerRootSeedDto.getCustomerSeedDtos()) {
            Customer customer = super.getModelMapper().map(customerSeedDto, Customer.class);
            this.customerRepository.save(customer);
        }
    }

    @Override
    public void getOrderedCustomers() throws JAXBException {
        Set<Customer> customers = this.customerRepository.orderedCustomers();
        Set<CustomerQ1Dto> customerDtos = new LinkedHashSet<>();

        for (Customer customer : customers) {
            Set<Sale> sales = this.saleRepository.findAllByCustomer(customer);
            customer.setSales(sales);
            CustomerQ1Dto customerDto = super.getModelMapper().map(customer, CustomerQ1Dto.class);
            customerDtos.add(customerDto);
        }
        CustomerRootQ1Dto customerRootQ1Dto = new CustomerRootQ1Dto();
        customerRootQ1Dto.setCustomerQ1Dtos(customerDtos);

        super.getXmlParser().exportToXml(customerRootQ1Dto, CustomerRootQ1Dto.class, OUTPUT_FILE_PATH_ORDERED_CUSTOMERS);

    }

    @Override
    public void allCustomersWhoBoughtACar() throws JAXBException {
        Set<Customer> customers = this.customerRepository.findAllWhoHaveBoughtAtLeast1Car();
        List<CustomerQ5Dto> customerQ5Dtos = new LinkedList<>();

        for (Customer customer : customers) {
            CustomerQ5Dto dto = super.getModelMapper().map(customer, CustomerQ5Dto.class);
            dto.setFullName(customer.getName());
            dto.setBoughtCars(customer.getSales().size());
            dto.setSpentMoney(calculateSpentMoney(customer));



            customerQ5Dtos.add(dto);
        }

        customerQ5Dtos = customerQ5Dtos.stream().sorted((c2, c1) -> {
                    if (!c1.getSpentMoney().equals(c2.getSpentMoney())) {
                        int money1 = c1.getSpentMoney().intValue();
                        int money2 = c2.getSpentMoney().intValue();
                        return money1 - money2;
                    } else {
                        return c1.getBoughtCars() - c2.getBoughtCars();
                    }
                }
        ).collect(Collectors.toList());

        CustomerQ5RootDto customerQ5RootDto = new CustomerQ5RootDto();
        customerQ5RootDto.setCustomerQ5Dtos(customerQ5Dtos);

        super.getXmlParser().exportToXml(customerQ5RootDto, CustomerQ5RootDto.class, OUTPUT_FILE_PATH_TOTAL_SALES_BY_CUSTOMER);
    }

    private BigDecimal calculateSpentMoney(Customer customer) {

        BigDecimal total = new BigDecimal(0);
        for (Sale sale : customer.getSales()) {
            Set<BigDecimal> prices = sale.getCar().getParts().stream().map(Part::getPrice).collect(Collectors.toSet());
            BigDecimal currentSum = new BigDecimal(0);
            for (BigDecimal price : prices) {
                currentSum = currentSum.add(price);
            }
            total = total.add(currentSum);
        }
        return total;
    }


}
