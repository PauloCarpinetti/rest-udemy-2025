package com.github.paulocarpinetti.rest_udemy_2025.repositories;

import com.github.paulocarpinetti.rest_udemy_2025.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
