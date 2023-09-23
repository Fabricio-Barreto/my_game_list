package com.plancto.mygamelist.services;

import com.plancto.mygamelist.dtos.UserDTO;
import com.plancto.mygamelist.models.user.UserModel;
import com.plancto.mygamelist.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
