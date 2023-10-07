package com.plancto.mygamelist.services;

import com.plancto.mygamelist.dtos.GenreDTO;
import com.plancto.mygamelist.models.exceptions.ResourceNotFoundException;
import com.plancto.mygamelist.models.game.GenreModel;
import com.plancto.mygamelist.repositories.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    /**
     * Method to get all Genres
     * @return List of Genres
     */
    public List<GenreDTO> getAllGenres() {
        List<GenreModel> genres = genreRepository.findAll();

        return genres.stream().map(genre -> new ModelMapper().map(genre, GenreDTO.class)).collect(Collectors.toList());
    }

    public Optional<GenreDTO> getGenreById(Long id) {
        Optional<GenreModel> genre = genreRepository.findById(id);
        if(genre.isEmpty()) throw new ResourceNotFoundException("Genre with id:" + id + "not found!");
        GenreDTO genreDTO = new ModelMapper().map(genre.get(),GenreDTO.class);
        return Optional.of(genreDTO);
    }
}
