package br.com.savebluapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.savebluapi.repositories.DeviceRepository;

@Service
public class DeviceService {
    
    @Autowired
    DeviceRepository repository;

    @Autowired
    ModelMapper mapper;

    
}
