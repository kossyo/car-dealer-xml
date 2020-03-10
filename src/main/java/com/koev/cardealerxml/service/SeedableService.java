package com.koev.cardealerxml.service;

import com.koev.cardealerxml.domain.dto.Dto;
import com.koev.cardealerxml.util.ValidatorUtil;
import com.koev.cardealerxml.util.tobedeleted.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.util.Random;
import java.util.Set;

@Component
public abstract class SeedableService extends BaseService implements Seedable {
    private final ValidatorUtil validatorUtil;
    private XmlParser xmlParser;

    protected SeedableService(ModelMapper modelMapper, Random random, ValidatorUtil validatorUtil, XmlParser xmlParser) {
        super(modelMapper, random);
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;

    }

    protected ValidatorUtil getValidatorUtil() {
        return validatorUtil;
    }

    public XmlParser getXmlParser() {
        return xmlParser;
    }

    @Override
    public boolean handleViolations(Dto dto) {
        Set<ConstraintViolation<Dto>> violations = this.validatorUtil.getViolations(dto);
        if (violations.size() > 0) {
            violations.forEach(viol -> System.out.println(viol.getMessage()));
            return true;
        }
        return false;
    }
}
