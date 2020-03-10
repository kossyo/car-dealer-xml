package com.koev.cardealerxml.controller;

import com.koev.cardealerxml.service.*;
import com.koev.cardealerxml.util.FileUtil;
//import com.koev.cardealerxml.domain.dto._01_ordered_customers.CustomerDto;
//import com.koev.cardealerxml.domain.dto._02_cars_from_make_toyota.CarDto;
//import com.koev.cardealerxml.domain.dto._03_local_suppliers.SupplierDto;
//import com.koev.cardealerxml.domain.dto._04_cars_with_list_of_parts.CarAndPartsWrapperDto;
//import com.koev.cardealerxml.domain.dto._05_total_sales_by_customer.CustomerQuery5Dto;
//import com.koev.cardealerxml.domain.dto._06_sales_with_applied_discount.SaleQuery6Dto;
//import com.koev.cardealerxml.domain.dto.seed.CarSeedDto;
//import com.koev.cardealerxml.domain.dto.seed.CustomerSeedDto;
//import com.koev.cardealerxml.domain.dto.seed.PartSeedDto;
//import com.koev.cardealerxml.domain.dto.seed.supplier.SupplierSeedDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AppController implements CommandLineRunner {

    private static final String CARS_JSON_FILE_PATH = "C:\\kossyo\\projects\\Softuni\\Hibernate\\Json Processing\\exercise_car_dealer\\src\\main\\resources\\json\\cars.json";
    private static final String CUSTOMERS_JSON_FILE_PATH = "C:\\kossyo\\projects\\Softuni\\Hibernate\\Json Processing\\exercise_car_dealer\\src\\main\\resources\\json\\customers.json";
    private static final String PARTS_JSON_FILE_PATH = "C:\\kossyo\\projects\\Softuni\\Hibernate\\Json Processing\\exercise_car_dealer\\src\\main\\resources\\json\\parts.json";
    private static final String SUPPLIERS_JSON_FILE_PATH = "C:\\kossyo\\projects\\Softuni\\Hibernate\\Json Processing\\exercise_car_dealer\\src\\main\\resources\\json\\suppliers.json";

    private final FileUtil fileUtil;

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final SaleService saleService;
    private final CustomerService customerService;

    public AppController(FileUtil fileUtil, SupplierService supplierService,
                         PartService partService,
                         CarService carService,
                         SaleService saleService,
                         CustomerService customerService) {

        this.fileUtil = fileUtil;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.saleService = saleService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
//        createDbRecords();
        orderedCustomers();
        carsFromMakeToyota();
        localSuppliers();
        carWithListOfParts();
        totalSalesByCustomer();
        salesWithAppliedDiscount();
    }

    //    Query 1 – Ordered Customers
    private void orderedCustomers() throws IOException, JAXBException {

        this.customerService.getOrderedCustomers();
    }
//
    //    Query 2 – Cars from make Toyota
    private void carsFromMakeToyota() throws IOException, JAXBException {
//        Set<CarDto> toyotas = this.carService.carsFromMakeToyota();
//        String toyotasString = this.gson.toJson(toyotas);
        this.carService.carsFromMakeToyota();
    }
//
    //    Query 3 – Local Suppliers
    private void localSuppliers() throws IOException, JAXBException {
        this.supplierService.localSuppliers();
    }
//
    //    Query 4 – Cars with Their List of Parts
    private void carWithListOfParts() throws IOException, JAXBException {
       this.carService.findAllCarsQuery4();
    }
//
    //    Query 5 – Total Sales by Customer
    private void totalSalesByCustomer() throws IOException, JAXBException {
     this.customerService.allCustomersWhoBoughtACar();
    }
//
    //    Query 6 – Sales with Applied Discount
    private void salesWithAppliedDiscount() throws IOException, JAXBException {
        this.saleService.salesWithAppliedDiscount();
    }













    private void createDbRecords() throws IOException, JAXBException {
//        seedSuppliers();
//        seedParts();
//        seedCars();
//        seedCustomers();
//        linkSales();
    }

    private void seedSuppliers() throws IOException, JAXBException {
//        String suppliersContent = this.fileUtil.getFileContent(SUPPLIERS_JSON_FILE_PATH);
//        SupplierSeedDto[] userSeedDtos = this.gson.fromJson(suppliersContent, SupplierSeedDto[].class);
        this.supplierService.seed();
    }

    private void seedParts() throws IOException, JAXBException {
        this.partService.seed();
    }
//
    private void seedCars() throws IOException, JAXBException {
        this.carService.seed();
    }
//
    private void seedCustomers() throws IOException, JAXBException {
        this.customerService.seed();
    }

    private void linkSales() {
        this.saleService.createSalesRelations();
    }



}
