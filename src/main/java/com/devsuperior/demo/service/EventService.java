package com.devsuperior.demo.service;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public EventDTO update(EventDTO dto, Long id) {
        Event event = repository.getReferenceById(id);
        if(repository.existsById(id)){
            event = repository.save(copyDTOtoEntity(dto, id));
        } else {
            throw new ResourceNotFoundException("Evento com id informado não encontrado.");
        }
        return new EventDTO(event);
    }

    public Event copyDTOtoEntity(EventDTO dto, Long id){
        Event event = new Event();
        event.setId(id);
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());
        event.setCity(new City(dto.getCityId(), ""));
        return event;
    }
}
