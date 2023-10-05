package com.plancto.mygamelist.models.game;

import com.plancto.mygamelist.enums.PlatformName;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_PLATFORM")
@Data
public class PlatformModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long platformId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PlatformName platformName;
}
