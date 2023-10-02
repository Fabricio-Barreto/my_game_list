package com.plancto.mygamelist.view.controllers;

import com.plancto.mygamelist.dtos.GameDTO;
import com.plancto.mygamelist.dtos.UserDTO;
import com.plancto.mygamelist.models.game.GameModel;
import com.plancto.mygamelist.services.GameService;
import com.plancto.mygamelist.services.UserService;
import com.plancto.mygamelist.view.models.GameRequest;
import com.plancto.mygamelist.view.models.GameResponse;
import com.plancto.mygamelist.view.models.UserRequest;
import com.plancto.mygamelist.view.models.UserResponse;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/game")
@Tag(name = "Game", description = "Endpoints for Managing Games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Finds all games", description = "Finds all games", tags = "Game", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = GameResponse.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<GameResponse>> getAllGames() {
        List<GameDTO> games = gameService.getAllGames();
        ModelMapper mapper = new ModelMapper();
        List<GameResponse> gameResponses = games.stream().map(game -> mapper.map(game, GameResponse.class)).collect(Collectors.toList());
        gameResponses.stream().forEach(gameResponse -> gameResponse.add(linkTo(methodOn(GameController.class).getGameById(gameResponse.getGameId())).withSelfRel()));
        return new ResponseEntity<>(gameResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Find a game", description = "Finds a game", tags = "Game", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GameResponse.class))
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Optional<GameResponse>> getGameById(@PathVariable Long id) {
        Optional<GameDTO> gameDTO = gameService.getGameById(id);
        GameResponse gameResponse = new ModelMapper().map(gameDTO.get(), GameResponse.class);
        gameResponse.add(linkTo(methodOn(GameController.class).getGameById(id)).withSelfRel());
        return new ResponseEntity<>(Optional.of(gameResponse), HttpStatus.OK);
    }

    @Operation(summary = "Add a new game", description = "Add a new game by passing in a JSON or XML", tags = "Game", responses = {
            @ApiResponse(description = "Created", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = GameResponse.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GameResponse> addGame(@RequestBody GameRequest gameRequest) {
        ModelMapper mapper = new ModelMapper();
        GameDTO gameDTO = mapper.map(gameRequest, GameDTO.class);
        gameDTO = gameService.addGame(gameDTO);
        GameResponse gameResponse = mapper.map(gameDTO, GameResponse.class);
        gameResponse.add(linkTo(methodOn(GameController.class).getGameById(gameResponse.getGameId())).withSelfRel());
        return new ResponseEntity<>(gameResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Deletes a game", description = "Deletes a game by passing in a JSON or XML", tags = "Game", responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        gameService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Updates a game", description = "Updates a game by passing in a JSON or XML", tags = "Game", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = GameResponse.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<GameResponse> updateGame(@RequestBody GameRequest gameRequest, @PathVariable Long id) {
        ModelMapper mapper = new ModelMapper();
        GameDTO gameDTO = mapper.map(gameRequest, GameDTO.class);
        gameDTO = gameService.updateGame(id, gameDTO);
        GameResponse gameResponse = mapper.map(gameDTO, GameResponse.class);
        gameResponse.add(linkTo(methodOn(GameController.class).updateGame(gameRequest, id)).withSelfRel());
        return new ResponseEntity<>(gameResponse, HttpStatus.OK);
    }
}
