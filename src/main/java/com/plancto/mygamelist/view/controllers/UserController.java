package com.plancto.mygamelist.view.controllers;

import com.plancto.mygamelist.dtos.UserDTO;
import com.plancto.mygamelist.models.game.GenreModel;
import com.plancto.mygamelist.services.UserService;
import com.plancto.mygamelist.view.models.requests.UserRequest;
import com.plancto.mygamelist.view.models.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "Endpoints for Managing Users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Finds all users", description = "Finds all users", tags = "User", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        ModelMapper mapper = new ModelMapper();
        List<UserResponse> usersResponse = users.stream().map(user -> mapper.map(user, UserResponse.class)).collect(Collectors.toList());
        usersResponse.stream().forEach(userResponse -> userResponse.add(linkTo(methodOn(UserController.class).getUserById(userResponse.getUserId())).withSelfRel()));
        return new ResponseEntity<>(usersResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Find a user", description = "Finds a user", tags = "User", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Optional<UserResponse>> getUserById(@PathVariable UUID id) {
        Optional<UserDTO> userDTO = userService.getUserById(id);
        UserResponse userResponse = new ModelMapper().map(userDTO.get(), UserResponse.class);
        userResponse.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());
        return new ResponseEntity<>(Optional.of(userResponse), HttpStatus.OK);
    }

    @Operation(summary = "Add a new user", description = "Add a new user by passing in a JSON or XML", tags = "User", responses = {
            @ApiResponse(description = "Created", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = User.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        ModelMapper mapper = new ModelMapper();
        UserDTO userDTO = mapper.map(userRequest, UserDTO.class);
        userDTO = userService.addUser(userDTO);
        UserResponse userResponse = mapper.map(userDTO, UserResponse.class);
        userResponse.add(linkTo(methodOn(UserController.class).addUser(userRequest)).withSelfRel());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Deletes a user", description = "Deletes a user by passing in a JSON or XML", tags = "User", responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Updates a user", description = "Updates a user by passing in a JSON or XML", tags = "User", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable UUID id) {
        ModelMapper mapper = new ModelMapper();
        UserDTO userDTO = mapper.map(userRequest, UserDTO.class);
        userDTO = userService.updateUser(id, userDTO);
        UserResponse userResponse = mapper.map(userDTO, UserResponse.class);
        userResponse.add(linkTo(methodOn(UserController.class).updateUser(userRequest, id)).withSelfRel());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

}
