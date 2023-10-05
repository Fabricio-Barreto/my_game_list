package com.plancto.mygamelist.services;

import com.plancto.mygamelist.dtos.GameDTO;
import com.plancto.mygamelist.models.exceptions.BadRequestException;
import com.plancto.mygamelist.models.exceptions.ResourceNotFoundException;
import com.plancto.mygamelist.models.game.GameModel;
import com.plancto.mygamelist.models.game.GenreModel;
import com.plancto.mygamelist.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    /**
     * method to get all games.
     * @return List of games.
     */
    public List<GameDTO> getAllGames(){
        List<GameModel> games = gameRepository.findAll();

        return games.stream().map(game -> new ModelMapper().map(game, GameDTO.class)).collect(Collectors.toList());
    }

    /**
     * method to add game.
     * @param gameDTO
     * @return added game
     */
    public GameDTO addGame(GameDTO gameDTO){
        GameModel game = new ModelMapper().map(gameDTO, GameModel.class);
        game = gameRepository.save(game);
        gameDTO.setGameId(game.getGameId());

        return gameDTO;
    }

    /**
     * method to get a game by id.
     * @param id
     * @return
     */
    public Optional<GameDTO> getGameById(Long id) {
        Optional<GameModel> game = gameRepository.findById(id);
        if(game.isEmpty()) {
            throw new ResourceNotFoundException("Game with id:" + id + "not found!");
        }
        GameDTO gameDTO = new ModelMapper().map(game.get(),GameDTO.class);
        return Optional.of(gameDTO);
    }

    /**
     * method to delete a Game.
     * @param id
     */
    public void deleteUser(Long id){
        Optional<GameModel> game = gameRepository.findById(id);
        if(game.isEmpty()){
            throw new ResourceNotFoundException("Game not found, Game with id:" + id + "not found to be deleted!");
        }
        gameRepository.deleteById(id);
    }

    /**
     * method to update a Game.
     * @param id
     * @param gameDTO
     * @return
     */
    public GameDTO updateGame(Long id, GameDTO gameDTO) {
        Optional<GameModel> gameOptional = gameRepository.findById(id);
        if(gameOptional.isEmpty()){
            throw new ResourceNotFoundException("Game not found, Game with id:" + id + "not found to be updated!");
        }
        gameDTO.setGameId(id);
        ModelMapper mapper = new ModelMapper();
        GameModel game = mapper.map(gameDTO, GameModel.class);

        try {
            gameRepository.save(game);
        } catch (BadRequestException ex) {
            ex.getMessage();
        }

        return gameDTO;
    }

}
