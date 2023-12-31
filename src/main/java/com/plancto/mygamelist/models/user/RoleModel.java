package com.plancto.mygamelist.models.user;

import com.plancto.mygamelist.enums.RoleName;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "TB_ROLE")
@Data
public class RoleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
