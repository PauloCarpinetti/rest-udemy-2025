package com.github.paulocarpinetti.rest_udemy_2025.services;

import com.github.paulocarpinetti.rest_udemy_2025.exceptions.ResourseNotFoundException;
import com.github.paulocarpinetti.rest_udemy_2025.model.Person;
import com.github.paulocarpinetti.rest_udemy_2025.repositories.PersonRepository;
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

    public Person findById(Long id){
        logger.info("Finding one Person!");
        return repository.findById(id)
                .orElseThrow( () -> new ResourseNotFoundException("No records found for this ID."));
    }

    public List<Person> findAll(){
        logger.info("Finding all People!");
        return repository.findAll();
    }

    public Person create(Person person){
        logger.info("Creating one person!");
        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating one person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow( () -> new ResourseNotFoundException("No records found for this ID."));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deleting one person!");
        Person entity = repository.findById(id)
                .orElseThrow( () -> new ResourseNotFoundException("No records found for this ID."));
        repository.delete(entity);
    }
}
