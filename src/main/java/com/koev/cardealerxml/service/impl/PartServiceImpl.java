package com.koev.cardealerxml.service.impl;

import com.koev.cardealerxml.constants.Constants;
import com.koev.cardealerxml.domain.dto.seed.part.PartSeedDto;
import com.koev.cardealerxml.domain.dto.seed.part.PartSeedRootDto;
import com.koev.cardealerxml.domain.entity.Part;
import com.koev.cardealerxml.domain.entity.Supplier;
import com.koev.cardealerxml.repository.PartRepository;
import com.koev.cardealerxml.repository.SupplierRepository;
import com.koev.cardealerxml.service.PartService;
import com.koev.cardealerxml.service.SeedableService;
import com.koev.cardealerxml.util.ValidatorUtil;
import com.koev.cardealerxml.util.tobedeleted.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class PartServiceImpl extends SeedableService implements PartService {

    private static final String INPUT_PARTS = Constants.XML_INPUT_PARTS;

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final Random random;
    protected PartServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper, PartRepository partRepository, SupplierRepository supplierRepository, Random random, XmlParser xmlParser) {
        super(modelMapper, random, validatorUtil, xmlParser);
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
//        this.random = random;
        this.random = random;
    }

//    @Override
//    public void seed(Dto[] partSeedDtos) {
//        List<Supplier> suppliers = this.supplierRepository.findAll();
//        for (Dto dto : partSeedDtos) {
//            Part part = super.getModelMapper().map(dto, Part.class);
//            Optional<Supplier> supplier = this.supplierRepository.findById(generateRandomNumber(suppliers.size()));
//            part.setSupplier(supplier.get());
//            this.partRepository.save(part);
//        }
//    }


    @Override
    public void seed() throws JAXBException {
        PartSeedRootDto partSeedRootDto = super.getXmlParser().parseXml(PartSeedRootDto.class, INPUT_PARTS);
        List<Supplier> suppliers = this.supplierRepository.findAll();
        for (PartSeedDto partSeedDto : partSeedRootDto.getPartSeedDtos()) {
            Part part = super.getModelMapper().map(partSeedDto, Part.class);
            Optional<Supplier> supplier = this.supplierRepository.findById(generateRandomNumber(suppliers.size()));
            part.setSupplier(supplier.get());
            this.partRepository.save(part);
        }
    }

    private int generateRandomNumber(int count) {

        return this.random.nextInt(count) + 1;
    }
}
