package com.plancto.mygamelist.view.controllers;

import com.plancto.mygamelist.dtos.GenreDTO;
import com.plancto.mygamelist.services.GenreService;
import com.plancto.mygamelist.view.models.responses.GenreResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Finds all genres", description = "Finds all genres", tags = "Genre", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = GenreResponse.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<GenreResponse>> getAllGenre() {
        List<GenreDTO> genres = genreService.getAllGenres();
        ModelMapper mapper = new ModelMapper();
        List<GenreResponse> genreResponses = genres.stream().map(genre -> mapper.map(genre, GenreResponse.class)).collect(Collectors.toList());
        genreResponses.stream().forEach(genreResponse -> genreResponse.add(linkTo(methodOn(GenreController.class).getGenreById(genreResponse.getGenreId())).withSelfRel()));
        return new ResponseEntity<>(genreResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Find a genre", description = "Finds a genre", tags = "Genre", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreResponse.class))
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Optional<GenreResponse>> getGenreById(@PathVariable Long id) {
        Optional<GenreDTO> genreDTO = genreService.getGenreById(id);
        GenreResponse genreResponse = new ModelMapper().map(genreDTO.get(), GenreResponse.class);
        genreResponse.add(linkTo(methodOn(GenreController.class).getGenreById(id)).withSelfRel());
        return new ResponseEntity<>(Optional.of(genreResponse), HttpStatus.OK);
    }
}
