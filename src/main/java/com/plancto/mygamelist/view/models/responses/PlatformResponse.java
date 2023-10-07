package com.plancto.mygamelist.view.models.responses;

import com.plancto.mygamelist.enums.PlatformName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatformResponse extends RepresentationModel<UserResponse>{
    private Long platformId;
    private PlatformName platformName;
}
