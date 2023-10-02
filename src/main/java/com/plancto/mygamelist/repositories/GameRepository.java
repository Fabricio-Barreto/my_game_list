package com.plancto.mygamelist.repositories;

import com.plancto.mygamelist.models.game.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameRepository extends JpaRepository<GameModel, Long> {
    //TODO Implementar um @Query para adicionar platform,genre,devloper passando os ID de cada um.
}
