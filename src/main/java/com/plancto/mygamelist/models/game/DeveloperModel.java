package com.plancto.mygamelist.models.game;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "TB_DEVELOPER")
@Data
public class DeveloperModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long developerId;
    @Column(nullable = false)
    private  String name;
    private String headquarter;
    private Date founded;
    private String ceo;
    private String website;
}
