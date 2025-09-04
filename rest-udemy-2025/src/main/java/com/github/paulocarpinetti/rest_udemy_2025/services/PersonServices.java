package com.github.paulocarpinetti.rest_udemy_2025.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.github.paulocarpinetti.rest_udemy_2025.controllers.PersonController;
import com.github.paulocarpinetti.rest_udemy_2025.data.dto.PersonDTO;
import com.github.paulocarpinetti.rest_udemy_2025.exceptions.RequiredObjectIsNullException;
import com.github.paulocarpinetti.rest_udemy_2025.exceptions.ResourceNotFoundException;
import com.github.paulocarpinetti.rest_udemy_2025.model.Person;
import com.github.paulocarpinetti.rest_udemy_2025.repositories.PersonRepository;

import static com.github.paulocarpinetti.rest_udemy_2025.mapper.DozerMapper.parseListObjects;
import static com.github.paulocarpinetti.rest_udemy_2025.mapper.DozerMapper.parseObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public PersonDTO findById(Long id){
        logger.info("Finding one Person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var dto = parseObject(entity, PersonDTO.class);
            addHateoasLinks(dto);
        return dto;
    }

    public List<PersonDTO> findAll(){
        logger.info("Finding all People!");
        var persons = parseListObjects(repository.findAll(), PersonDTO.class);
            persons.forEach(this::addHateoasLinks);
        return persons;
    }

    public PersonDTO create(PersonDTO person){
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one person!");
        var entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity), PersonDTO.class);
            addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO update(PersonDTO person){
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow( () -> new ResourceNotFoundException("No records found for this ID."));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var dto = parseObject(repository.save(entity), PersonDTO.class);
            addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
