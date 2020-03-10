package com.koev.cardealerxml.service.impl;

import com.koev.cardealerxml.constants.Constants;
import com.koev.cardealerxml.domain.dto._03_local_suppliers.SupplierQ3Dto;
import com.koev.cardealerxml.domain.dto._03_local_suppliers.SupplierQ3RootDto;
import com.koev.cardealerxml.domain.dto.seed.supplier.SupplierSeedDto;
import com.koev.cardealerxml.domain.dto.seed.supplier.SupplierSeedRootDto;
import com.koev.cardealerxml.domain.entity.Supplier;
import com.koev.cardealerxml.repository.SupplierRepository;
import com.koev.cardealerxml.service.SeedableService;
import com.koev.cardealerxml.service.SupplierService;
import com.koev.cardealerxml.util.ValidatorUtil;
import com.koev.cardealerxml.util.tobedeleted.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;


@Service
public class SupplierServiceImpl extends SeedableService implements SupplierService {

    private static final String INPUT_SUPPLIERS = Constants.XML_INPUT_SUPPLIERS;
    private static final String OUTPUT_FILE_PATH_LOCAL_SUPPLIERS = Constants.OUTPUT_FILE_PATH_LOCAL_SUPPLIERS;
    private final SupplierRepository supplierRepository;
    private final Random random;

    protected SupplierServiceImpl(ValidatorUtil validatorUtil, ModelMapper modelMapper,
                                  SupplierRepository supplierRepository, Random random, XmlParser xmlParser) {
        super(modelMapper, random, validatorUtil, xmlParser);
        this.supplierRepository = supplierRepository;
        this.random = random;
    }

    @Override
    public void seed() throws JAXBException {
        SupplierSeedRootDto supplierSeedRootDto = super.getXmlParser().parseXml(SupplierSeedRootDto.class, INPUT_SUPPLIERS);


        for (SupplierSeedDto supplierSeedDto : supplierSeedRootDto.getSupplierSeedDtos()) {
            Supplier supplier = super.getModelMapper().map(supplierSeedDto, Supplier.class);
            this.supplierRepository.save(supplier);
        }
        System.out.println();
    }

    @Override
    public void localSuppliers() throws JAXBException {
        Set<Supplier> suppliers = this.supplierRepository.localSuppliers();
        Set<SupplierQ3Dto> supplierDtos = new LinkedHashSet<>();

        for (Supplier supplier : suppliers) {
            SupplierQ3Dto dto = super.getModelMapper().map(supplier, SupplierQ3Dto.class);
            dto.setPartsCount(supplier.getParts().size());
            supplierDtos.add(dto);
        }

        SupplierQ3RootDto supplierQ3RootDto = new SupplierQ3RootDto();
        supplierQ3RootDto.setSupplierQ3Dtos(supplierDtos);
        super.getXmlParser().exportToXml(supplierQ3RootDto, SupplierQ3RootDto.class, OUTPUT_FILE_PATH_LOCAL_SUPPLIERS);
    }
}
