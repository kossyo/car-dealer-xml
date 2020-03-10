package com.koev.cardealerxml.constants;

public final class Constants {

    private static final String XML_INPUT_FOLDER = "C:\\kossyo\\projects\\Softuni\\Hibernate\\Xml processing\\car dealer\\src\\main\\resources\\input\\";
    public static final String XML_INPUT_SUPPLIERS = XML_INPUT_FOLDER + "suppliers.xml";
    public static final String XML_INPUT_PARTS = XML_INPUT_FOLDER + "parts.xml";
    public static final String XML_INPUT_CARS = XML_INPUT_FOLDER + "cars.xml";
    public static final String XML_INPUT_CUSTOMERS = XML_INPUT_FOLDER + "customers.xml";

    private static final String OUTPUT_FILE_PATH =
            "C:\\kossyo\\projects\\Softuni\\Hibernate\\Xml processing\\car dealer\\output\\";

    public static final String OUTPUT_FILE_PATH_ORDERED_CUSTOMERS = OUTPUT_FILE_PATH + "ordered_customers.txt";
    public static final String OUTPUT_FILE_PATH_TOYOTA_CARS = OUTPUT_FILE_PATH + "toyota_cars.txt";
    public static final String OUTPUT_FILE_PATH_LOCAL_SUPPLIERS = OUTPUT_FILE_PATH +  "local_suppliers.txt";
    public static final String OUTPUT_FILE_PATH_CARS_AND_PARTS = OUTPUT_FILE_PATH + "cars_and_parts.txt";
    public static final String OUTPUT_FILE_PATH_TOTAL_SALES_BY_CUSTOMER = OUTPUT_FILE_PATH + "total_sales_by_customer.txt";
    public static final String OUTPUT_FILE_PATH_SALES_WITH_APPLIED_DISCOUNT = OUTPUT_FILE_PATH + "sales_with_applied_discount.txt";

    public static final int NUMBER_OF_SALES = 150;
    public static final int NUMBER_OF_DISCOUNTS = 8;

    private Constants(){}
}
