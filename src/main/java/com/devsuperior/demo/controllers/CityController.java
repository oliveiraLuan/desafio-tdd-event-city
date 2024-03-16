package com.devsuperior.demo.controllers;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.service.CityService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(){
        List<CityDTO> cities = cityService.findAll();
        cities.sort(Comparator.comparing(city -> city.getName()));
        return ResponseEntity.ok(cities);
    }
    @PostMapping
    public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto){
        CityDTO city = cityService.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(city)
                .toUri();
        return ResponseEntity.created(uri).body(city);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
