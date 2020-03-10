package com.koev.cardealerxml.service;

import org.modelmapper.ModelMapper;

import java.util.Random;

public abstract class BaseService {

    private final ModelMapper modelMapper;
    private final Random random;

    protected BaseService(ModelMapper modelMapper, Random random) {
        this.modelMapper = modelMapper;
        this.random = random;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public Random getRandom() {
        return random;
    }
}
