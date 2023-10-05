package com.plancto.mygamelist.dtos;

import com.plancto.mygamelist.enums.PlatformName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatformDTO {
    private Long platformId;
    private PlatformName platformName;
}
