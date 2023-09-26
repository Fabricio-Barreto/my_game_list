package com.plancto.mygamelist.services;

import com.plancto.mygamelist.dtos.UserDTO;
import com.plancto.mygamelist.models.exceptions.BadRequestException;
import com.plancto.mygamelist.models.exceptions.ResourceNotFoundException;
import com.plancto.mygamelist.models.user.UserModel;
import com.plancto.mygamelist.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * method to get all users.
     * @return List of users.
     */
    public List<UserDTO> getAllUsers(){
        List<UserModel> users = userRepository.findAll();

        return users.stream().map(user -> new ModelMapper().map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO addUser(UserDTO userDTO){
        UserModel user = new ModelMapper().map(userDTO, UserModel.class);
        user = userRepository.save(user);
        userDTO.setUserId(user.getUserId());

        return userDTO;
    }

    public Optional<UserDTO> getUserById(UUID id) {
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("User with id:" + id + "not found!");
        }
        UserDTO userDTO = new ModelMapper().map(user.get(),UserDTO.class);
        return Optional.of(userDTO);
    }

    public void deleteUser(UUID id){
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User not found, User with id:" + id + "not found to be deleted!");
        }
        userRepository.deleteById(id);
    }

    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new ResourceNotFoundException("User not found, User with id:" + id + "not found to be updated!");
        }
        userDTO.setUserId(id);
        ModelMapper mapper = new ModelMapper();
        UserModel user = mapper.map(userDTO, UserModel.class);

        try {
            userRepository.save(user);
        } catch (BadRequestException ex) {
            ex.getMessage();
        }

        return userDTO;
    }
}
