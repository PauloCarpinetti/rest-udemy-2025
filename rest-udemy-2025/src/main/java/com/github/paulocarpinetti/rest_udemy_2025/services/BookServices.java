package com.github.paulocarpinetti.rest_udemy_2025.services;

import com.github.paulocarpinetti.rest_udemy_2025.controllers.BookControllerV1;
import com.github.paulocarpinetti.rest_udemy_2025.data.dto.BookDTO;
import com.github.paulocarpinetti.rest_udemy_2025.exceptions.RequiredObjectIsNullException;
import com.github.paulocarpinetti.rest_udemy_2025.exceptions.ResourceNotFoundException;
import com.github.paulocarpinetti.rest_udemy_2025.mapper.DozerMapper;
import com.github.paulocarpinetti.rest_udemy_2025.model.Book;
import com.github.paulocarpinetti.rest_udemy_2025.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookDTO> findAll(){
        logger.info("Finding all Books!");

        var books = DozerMapper.parseListObjects(repository.findAll(), BookDTO.class);

        books
                .stream()
                .forEach(b -> b.add(linkTo(methodOn(BookControllerV1.class)
                        .findById(b.getKey())).withSelfRel()));

        return books;
    }

    public BookDTO findById(Long id) {
        logger.info("Finding one Book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No records found for this id."));

        var vo =  DozerMapper.parseObject(entity, BookDTO.class);
        vo.add(linkTo(methodOn(BookControllerV1.class)
                .findById(vo.getKey())).withSelfRel());

        return vo;

    }

    public BookDTO create(BookDTO book) {

        if ( book == null ) throw new RequiredObjectIsNullException();
        logger.info("Creating a new Book!");

        var entity = DozerMapper.parseObject(book, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BookDTO.class);
        vo.add(linkTo(methodOn(BookControllerV1.class)
                .findById(vo.getKey())).withSelfRel());
        return vo;

    }

    public BookDTO update(BookDTO book) {
        if (book == null ) throw new RequiredObjectIsNullException();
        logger.info("Updating one Book!");
        var entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No records found for this id."));
        entity.setAuthor(book.getAuthor());;
        entity.setTitle(book.getTitle());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        var vo = DozerMapper.parseObject(repository.save(entity), BookDTO.class);
        vo.add(linkTo(methodOn(BookControllerV1.class)
                .findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one Book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No records found for this id."));

        repository.delete(entity);
    }
}

