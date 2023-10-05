package com.plancto.mygamelist.view.models.responses;

import com.plancto.mygamelist.models.user.LocationModel;
import com.plancto.mygamelist.models.user.PhoneModel;
import com.plancto.mygamelist.models.user.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends RepresentationModel<UserResponse> {
    private UUID userId;
    private String email;
    private String password;
    private List<RoleModel> role;
    private List<PhoneModel> phone;
    private LocationModel location;
}

