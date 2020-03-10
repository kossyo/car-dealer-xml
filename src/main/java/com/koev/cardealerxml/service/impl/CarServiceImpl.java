package com.koev.cardealerxml.service.impl;


import com.koev.cardealerxml.constants.Constants;
import com.koev.cardealerxml.domain.dto.Dto;
import com.koev.cardealerxml.domain.dto._02_cars_from_make_toyota.CarQ2Dto;
import com.koev.cardealerxml.domain.dto._02_cars_from_make_toyota.CarRootQ2Dto;
import com.koev.cardealerxml.domain.dto._04_cars_with_list_of_parts.CarQ4Dto;
import com.koev.cardealerxml.domain.dto._04_cars_with_list_of_parts.CarQ4RootDto;
import com.koev.cardealerxml.domain.dto._04_cars_with_list_of_parts.PartQ4Dto;
import com.koev.cardealerxml.domain.dto._04_cars_with_list_of_parts.PartQ4RootDto;
import com.koev.cardealerxml.domain.dto.seed.car.CarSeedDto;
import com.koev.cardealerxml.domain.dto.seed.car.CarSeedRootDto;
import com.koev.cardealerxml.domain.entity.Car;
import com.koev.cardealerxml.domain.entity.Part;
import com.koev.cardealerxml.repository.CarRepository;
import com.koev.cardealerxml.repository.PartRepository;
import com.koev.cardealerxml.service.CarService;
import com.koev.cardealerxml.service.SeedableService;
import com.koev.cardealerxml.util.ValidatorUtil;
import com.koev.cardealerxml.util.tobedeleted.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CarServiceImpl extends SeedableService implements CarService {

    private static final String XML_INPUT_CARS = Constants.XML_INPUT_CARS;
    private static final String OUTPUT_FILE_PATH_TOYOTA_CARS = Constants.OUTPUT_FILE_PATH_TOYOTA_CARS;
    private static final String OUTPUT_FILE_PATH_CARS_AND_PARTS = Constants.OUTPUT_FILE_PATH_CARS_AND_PARTS;

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final Random random;


    protected CarServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, CarRepository carRepository,
                             PartRepository partRepository, Random random, XmlParser xmlParser) {
        super(modelMapper, random, validatorUtil, xmlParser);
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.random = random;
    }


    @Override
    public void carsFromMakeToyota() throws JAXBException {
        Set<Car> cars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        Set<CarQ2Dto> carDtos = new LinkedHashSet<>();

        for (Car car : cars) {
            CarQ2Dto carDto = super.getModelMapper().map(car, CarQ2Dto.class);
            carDtos.add(carDto);
        }
        CarRootQ2Dto carRootQ2Dto = new CarRootQ2Dto();
        carRootQ2Dto.setCarQ2Dtos(carDtos);

        super.getXmlParser().exportToXml(carRootQ2Dto, CarRootQ2Dto.class, OUTPUT_FILE_PATH_TOYOTA_CARS);

    }

    //
    @Override
    public void findAllCarsQuery4() throws JAXBException {

        List<Car> cars = this.carRepository.findAll();
        //collection of car inner dtos
        Set<CarQ4Dto> carQ4Dtos = new LinkedHashSet<>();

        for (Car car : cars) {


            // get parts of this car
            Set<Part> parts = car.getParts();
            Set<PartQ4Dto> partQ4Dtos = new LinkedHashSet<>();
            for (Part part : parts) {
                PartQ4Dto partQ4Dto = super.getModelMapper().map(part, PartQ4Dto.class);
                partQ4Dtos.add(partQ4Dto);
            }
            PartQ4RootDto partQ4RootDto = new PartQ4RootDto();
            partQ4RootDto.setPartQ4Dtos(partQ4Dtos);

            CarQ4Dto carQ4Dto = super.getModelMapper().map(car, CarQ4Dto.class);
            carQ4Dto.setPartQ4RootDto(partQ4RootDto);
            carQ4Dtos.add(carQ4Dto);


        }
        CarQ4RootDto carQ4RootDto = new CarQ4RootDto();
        carQ4RootDto.setCarQ4Dtos(carQ4Dtos);

        super.getXmlParser().exportToXml(carQ4RootDto, CarQ4RootDto.class, OUTPUT_FILE_PATH_CARS_AND_PARTS);


    }

    @Override
    public void seed() throws JAXBException {
        CarSeedRootDto carSeedRootDto = super.getXmlParser().parseXml(CarSeedRootDto.class, XML_INPUT_CARS);
        List<Part> parts = this.partRepository.findAll();

        for (CarSeedDto carSeedDto : carSeedRootDto.getCarSeedDto()) {
            Car car = super.getModelMapper().map(carSeedDto, Car.class);
            assignPartsToCar(car, parts);
            this.carRepository.save(car);
        }
    }

    private void assignPartsToCar(Car car, List<Part> parts) {

        int howManyPartsToAssign = generateRandomNumber(10) + 10;
        int partsCount = parts.size();
        for (int i = 0; i < howManyPartsToAssign; i++) {
            int idOfRandomPart = generateRandomNumber(partsCount - 1);
            Part part = parts.get(idOfRandomPart);
            car.addPart(part);
        }
    }

    //    todo: try to pull this random up
    private int generateRandomNumber(int count) {

        return this.random.nextInt(count) + 1;
    }

}
