package com.plancto.mygamelist.repositories;

import com.plancto.mygamelist.enums.RoleName;
import com.plancto.mygamelist.models.user.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleModel, UUID> {
    Optional<RoleModel> findByRoleName(RoleName roleName);

}
