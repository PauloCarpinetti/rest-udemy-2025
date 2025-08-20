package com.github.paulocarpinetti.rest_udemy_2025.repositories;

import com.github.paulocarpinetti.rest_udemy_2025.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
