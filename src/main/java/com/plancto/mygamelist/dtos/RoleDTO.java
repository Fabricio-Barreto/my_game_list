package com.plancto.mygamelist.dtos;

import com.plancto.mygamelist.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private UUID roleId;
    private RoleName roleName;
}
