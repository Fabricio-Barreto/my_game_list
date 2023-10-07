package com.plancto.mygamelist.view.controllers;

import com.plancto.mygamelist.dtos.GenreDTO;
import com.plancto.mygamelist.dtos.PlatformDTO;
import com.plancto.mygamelist.services.PlatformService;
import com.plancto.mygamelist.view.models.responses.GenreResponse;
import com.plancto.mygamelist.view.models.responses.PlatformResponse;
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
@RequestMapping("/api/v1/platform")
public class PlatformController {

    @Autowired
    PlatformService platformService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Finds all platforms", description = "Finds all platforms", tags = "Platform", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PlatformResponse.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<PlatformResponse>> getAllPlatform() {
        List<PlatformDTO> platforms = platformService.getAllPlatform();
        ModelMapper mapper = new ModelMapper();
        List<PlatformResponse> platformResponses = platforms.stream().map(genre -> mapper.map(genre, PlatformResponse.class)).collect(Collectors.toList());
        platformResponses.stream().forEach(platformResponse -> platformResponse.add(linkTo(methodOn(PlatformController.class).getPlatformById(platformResponse.getPlatformId())).withSelfRel()));
        return new ResponseEntity<>(platformResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Find a platform", description = "Finds a platform", tags = "Platform", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlatformResponse.class))
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Optional<PlatformResponse>> getPlatformById(@PathVariable Long id) {
        Optional<PlatformDTO> platformDTO = platformService.getPlatformById(id);
        PlatformResponse platformResponse = new ModelMapper().map(platformDTO.get(), PlatformResponse.class);
        platformResponse.add(linkTo(methodOn(GenreController.class).getGenreById(id)).withSelfRel());
        return new ResponseEntity<>(Optional.of(platformResponse), HttpStatus.OK);
    }
}
