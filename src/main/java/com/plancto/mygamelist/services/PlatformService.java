package com.plancto.mygamelist.services;

import com.plancto.mygamelist.dtos.PlatformDTO;
import com.plancto.mygamelist.models.exceptions.ResourceNotFoundException;
import com.plancto.mygamelist.models.game.PlatformModel;
import com.plancto.mygamelist.repositories.PlatformRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlatformService {
    @Autowired
    PlatformRepository platformRepository;

    /**
     * Method to get all platforms.
     * @return
     */
    public List<PlatformDTO> getAllPlatform() {
        List<PlatformModel> platforms = platformRepository.findAll();

        return platforms.stream().map(platform -> new ModelMapper().map(platform, PlatformDTO.class)).collect(Collectors.toList());
    }

    /**
     * Method to get a platform by Id.
     * @param id
     * @return
     */
    public Optional<PlatformDTO> getPlatformById(Long id) {
        Optional<PlatformModel> platform = platformRepository.findById(id);
        if(platform.isEmpty()) throw new ResourceNotFoundException("Platform with id:" + id + "not found!");
        PlatformDTO platformDTO = new ModelMapper().map(platform, PlatformDTO.class);
        return Optional.of(platformDTO);
    }
}
