package com.plancto.mygamelist.repositories;

import com.plancto.mygamelist.models.game.PlatformModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<PlatformModel,Long> {
}
