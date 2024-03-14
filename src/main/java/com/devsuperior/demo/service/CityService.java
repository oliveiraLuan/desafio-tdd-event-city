package com.devsuperior.demo.service;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;


    public List<CityDTO> findAll() {
        List<City> cities = repository.findAll();
        return cities
                .stream()
                .map(city -> new CityDTO(city))
                .collect(Collectors.toList());
    }
}
