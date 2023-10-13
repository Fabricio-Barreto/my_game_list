package com.plancto.mygamelist.view.models.responses;

import com.plancto.mygamelist.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse extends RepresentationModel<RoleResponse> {
    private UUID roleId;
    private RoleName roleName;
}
