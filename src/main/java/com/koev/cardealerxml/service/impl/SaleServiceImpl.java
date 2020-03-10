package com.koev.cardealerxml.service.impl;

import com.koev.cardealerxml.constants.Constants;
import com.koev.cardealerxml.domain.dto._06_sales_with_applied_discount.CarQ6Dto;
import com.koev.cardealerxml.domain.dto._06_sales_with_applied_discount.SaleQ6Dto;
import com.koev.cardealerxml.domain.dto._06_sales_with_applied_discount.SaleQ6RootDto;
import com.koev.cardealerxml.domain.entity.Car;
import com.koev.cardealerxml.domain.entity.Customer;
import com.koev.cardealerxml.domain.entity.Sale;
import com.koev.cardealerxml.repository.CarRepository;
import com.koev.cardealerxml.repository.CustomerRepository;
import com.koev.cardealerxml.repository.SaleRepository;
import com.koev.cardealerxml.service.BaseService;
import com.koev.cardealerxml.service.SaleService;
import com.koev.cardealerxml.util.tobedeleted.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl extends BaseService implements SaleService {

    private static final String OUTPUT_FILE_PATH_SALES_WITH_APPLIED_DISCOUNT = Constants.OUTPUT_FILE_PATH_SALES_WITH_APPLIED_DISCOUNT;

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final EntityManager em;
    private final XmlParser xmlParser;

    public SaleServiceImpl(ModelMapper modelMapper, Random random, SaleRepository saleRepository,
                           CarRepository carRepository,
                           CustomerRepository customerRepository,
                           EntityManager em,
                           XmlParser xmlParser, XmlParser xmlParser1) {
        super(modelMapper, random);
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.em = em;
        this.xmlParser = xmlParser1;
    }

    @Override
    @Transactional
    public void createSalesRelations() {
        List<Customer> customers = this.customerRepository.findAll();
        int customersCount = customers.size();
        List<Car> cars = this.carRepository.findAll();
        int carsCount = cars.size();

        for (int i = 0; i < Constants.NUMBER_OF_SALES; i++) {

            int customerId = super.getRandom().nextInt(customersCount);
            Customer customer = customers.get(customerId);

            int carId = super.getRandom().nextInt(carsCount);
            Car car = cars.get(carId);

            double discount = getDiscount();

            this.em.merge(customer);
            this.em.merge(car);

            Sale sale = new Sale(customer, car, discount);
            this.saleRepository.save(sale);
        }
    }

    @Override
    public void salesWithAppliedDiscount() throws JAXBException {
        List<Sale> sales = this.saleRepository.findAll();
        Set<SaleQ6Dto> saleQ6Dtos = new LinkedHashSet<>();
        for (Sale sale : sales) {
            CarQ6Dto carQ6Dto = super.getModelMapper().map(sale.getCar(), CarQ6Dto.class);
            SaleQ6Dto saleQ6Dto = new SaleQ6Dto();
            saleQ6Dto.setCar(carQ6Dto);
            saleQ6Dto.setCustomerName(sale.getCustomer().getName());
            saleQ6Dto.setDiscount(sale.getDiscount());
            BigDecimal carPriceWithoutDiscount = getCarPriceWithoutDiscount(sale);
            saleQ6Dto.setPriceWithDiscount(carPriceWithoutDiscount
                    .multiply(
                            new BigDecimal(1).subtract(BigDecimal.valueOf(sale.getDiscount()))
                    )
            );
            saleQ6Dto.setPrice(carPriceWithoutDiscount);
            saleQ6Dtos.add(saleQ6Dto);
        }
        SaleQ6RootDto saleQ6RootDto = new SaleQ6RootDto();
        saleQ6RootDto.setSaleQ6Dto(saleQ6Dtos);
        this.xmlParser.exportToXml(saleQ6RootDto, SaleQ6RootDto.class, OUTPUT_FILE_PATH_SALES_WITH_APPLIED_DISCOUNT);
    }



    private BigDecimal getCarPriceWithoutDiscount(Sale sale) {
        List<Double> partPrices = sale.getCar().getParts().stream().map(p -> p.getPrice().doubleValue()).collect(Collectors.toList());
        BigDecimal total = new BigDecimal(0);
        for (Double partPrice : partPrices) {
            total = total.add(BigDecimal.valueOf(partPrice));
        }
        return total;
    }

    private double getDiscount() {
        List<Double> possibleDiscounts = new ArrayList<>();
        possibleDiscounts.add(0.0);
        possibleDiscounts.add(0.5);
        possibleDiscounts.add(0.10);
        possibleDiscounts.add(0.15);
        possibleDiscounts.add(0.20);
        possibleDiscounts.add(0.30);
        possibleDiscounts.add(0.40);
        possibleDiscounts.add(0.50);

        return possibleDiscounts.get(super.getRandom().nextInt(Constants.NUMBER_OF_DISCOUNTS));
    }
}
