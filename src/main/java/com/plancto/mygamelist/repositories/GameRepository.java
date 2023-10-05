package com.plancto.mygamelist.repositories;

import com.plancto.mygamelist.models.game.GameModel;
import com.plancto.mygamelist.models.game.GenreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<GameModel, Long> {
    //TODO Implementar um @Query para adicionar platform,genre,devloper passando os ID de cada um.
}
