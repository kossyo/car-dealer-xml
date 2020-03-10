package com.koev.cardealerxml.configuration;

import com.koev.cardealerxml.service.SaleService;
import com.koev.cardealerxml.service.impl.SaleServiceImpl;
import com.koev.cardealerxml.util.FileUtil;
import com.koev.cardealerxml.util.FileUtilImpl;
import com.koev.cardealerxml.util.*;
import com.koev.cardealerxml.util.tobedeleted.XmlParser;
import com.koev.cardealerxml.util.tobedeleted.XmlParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Random;

@Configuration
public class ApplicationBeanConfiguration {



    @Bean
    public FileUtil fileUtil(){
        return new FileUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidatorUtil validatorUtil(){
        return new ValidatorUtilImpl(validator());
    }

    @Bean
    public Random random(){
        return new Random();
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }



}
