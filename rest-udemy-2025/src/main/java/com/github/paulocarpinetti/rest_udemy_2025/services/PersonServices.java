package com.github.paulocarpinetti.rest_udemy_2025.services;

import com.github.paulocarpinetti.rest_udemy_2025.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        logger.info("Finding one Person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Paulo");
        person.setLastName("Carpinetti");
        person.setAddress("São Paulo - SP - Brasil");
        person.setGender("Male");
        return person;
    }

    public List<Person> findAll(){
        logger.info("Finding all People!");
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 8; i ++){
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person){

        logger.info("Creating one person!");
        return person;
    }

    public Person update(Person person){

        logger.info("Updating one person!");
        return person;
    }

    public void delete(String id){

        logger.info("Deleting one person!");

    }



    private Person mockPerson(int i){

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("First Name" + i);
        person.setLastName("Last Name" + i);
        person.setAddress("São Paulo - SP - Brasil");
        person.setGender("Male");
        return person;
    }

}
