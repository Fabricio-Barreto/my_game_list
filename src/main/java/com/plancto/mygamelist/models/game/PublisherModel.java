package com.plancto.mygamelist.models.game;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "TB_PUBLISHER")
@Data
public class PublisherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long publisherId;
    @Column(nullable = false)
    private  String name;
    private String headquarter;
    private Date founded;
    private String ceo;
    private String website;
}
