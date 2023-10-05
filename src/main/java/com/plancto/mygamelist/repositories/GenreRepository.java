package com.plancto.mygamelist.repositories;

import com.plancto.mygamelist.models.game.GenreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreModel,Long> {
}
