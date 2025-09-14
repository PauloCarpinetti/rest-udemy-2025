package com.github.paulocarpinetti.rest_udemy_2025.controllers;

import com.github.paulocarpinetti.rest_udemy_2025.data.dto.BookDTO;
import com.github.paulocarpinetti.rest_udemy_2025.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books/v1")
@Tag(name = "Books", description = "Endpoints for Managing Books." )
public class BookControllerV1 {

    @Autowired
    private BookServices service;

    @GetMapping()
    @Operation(summary = "Finds all Books.", description = "Finds all Books.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(
                                            implementation = BookDTO.class)
                                    )
                            )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",
                            content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",
                            content = @Content)
            })
    public List<BookDTO> findAll() {

        return service.findAll();

    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Finds one Book.", description = "Finds one Book.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDTO.class))
                    ),
                    @ApiResponse(description = "No content", responseCode = "204",
                            content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",
                            content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",
                            content = @Content)
            })
    public BookDTO findById(@PathVariable(value= "id") Long id) {

        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Creates a new book.", description = "Creates a new book.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDTO.class))
                    ),
                    @ApiResponse(description = "No content", responseCode = "204",
                            content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",
                            content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",
                            content = @Content)
            })
    public BookDTO create(@RequestBody BookDTO book) {

        return service.create(book);
    }

    @PutMapping
    @Operation(summary = "Updates a book.", description = "Updates a book.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDTO.class))
                    ),
                    @ApiResponse(description = "No content", responseCode = "204",
                            content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",
                            content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",
                            content = @Content)
            })
    public BookDTO update(@RequestBody BookDTO book) {

        return service.update(book);

    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a book.", description = "Deletes a book.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204",
                            content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401",
                            content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500",
                            content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value= "id") Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();

    }

}

