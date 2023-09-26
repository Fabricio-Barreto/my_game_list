package com.plancto.mygamelist.view.controllers;

import com.plancto.mygamelist.dtos.UserDTO;
import com.plancto.mygamelist.services.UserService;
import com.plancto.mygamelist.view.models.UserRequest;
import com.plancto.mygamelist.view.models.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        ModelMapper mapper = new ModelMapper();
        List<UserResponse> usersResponse = users.stream().map(user -> mapper.map(user, UserResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(usersResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Optional<UserResponse>> getUserById(@PathVariable UUID id) {
        Optional<UserDTO> userDTO = userService.getUserById(id);
        UserResponse userResponse = new ModelMapper().map(userDTO.get(), UserResponse.class);
        return new ResponseEntity<>(Optional.of(userResponse), HttpStatus.OK);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        ModelMapper mapper = new ModelMapper();
        UserDTO userDTO = mapper.map(userRequest, UserDTO.class);
        userDTO = userService.addUser(userDTO);

        return new ResponseEntity<>(mapper.map(userDTO, UserResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable UUID id) {
        ModelMapper mapper = new ModelMapper();
        UserDTO userDTO = mapper.map(userRequest, UserDTO.class);
        userDTO = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(mapper.map(userDTO, UserResponse.class), HttpStatus.OK);
    }
}
