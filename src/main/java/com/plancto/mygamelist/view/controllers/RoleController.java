package com.plancto.mygamelist.view.controllers;

import com.plancto.mygamelist.dtos.GenreDTO;
import com.plancto.mygamelist.dtos.RoleDTO;
import com.plancto.mygamelist.enums.RoleName;
import com.plancto.mygamelist.repositories.RoleRepository;
import com.plancto.mygamelist.services.RoleService;
import com.plancto.mygamelist.view.models.responses.GenreResponse;
import com.plancto.mygamelist.view.models.responses.RoleResponse;
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
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Finds all roles", description = "Finds all roles", tags = "Role", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RoleResponse.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<RoleResponse>> getAllRole() {
        List<RoleDTO> roles = roleService.getAllRoles();
        ModelMapper mapper = new ModelMapper();
        List<RoleResponse> roleResponses = roles.stream().map(role -> mapper.map(role, RoleResponse.class)).collect(Collectors.toList());
        roleResponses.stream().forEach(roleResponse -> roleResponse.add(linkTo(methodOn(RoleController.class).getRoleById(roleResponse.getRoleId())).withSelfRel()));
        return new ResponseEntity<>(roleResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Find a role", description = "Finds a role", tags = "Role", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Optional<RoleResponse>> getRoleById(@PathVariable UUID id) {
        Optional<RoleDTO> roleDTO = roleService.getRoleById(id);
        RoleResponse roleResponse = new ModelMapper().map(roleDTO.get(), RoleResponse.class);
        roleResponse.add(linkTo(methodOn(RoleController.class).getRoleById(id)).withSelfRel());
        return new ResponseEntity<>(Optional.of(roleResponse), HttpStatus.OK);
    }

    @GetMapping(value = "/name={roleName}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Find a role by name", description = "Finds a role by name", tags = "Role", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Optional<RoleResponse>> getRoleByRoleName(@PathVariable RoleName roleName) {
        Optional<RoleDTO> roleDTO = roleService.getRoleByRoleName(roleName);
        RoleResponse roleResponse = new ModelMapper().map(roleDTO.get(), RoleResponse.class);
        roleResponse.add(linkTo(methodOn(RoleController.class).getRoleByRoleName(roleName)).withSelfRel());
        return new ResponseEntity<>(Optional.of(roleResponse), HttpStatus.OK);
    }
}
