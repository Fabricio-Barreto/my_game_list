package com.plancto.mygamelist.models.game;

import com.plancto.mygamelist.enums.GenreName;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_GENRE")
@Data
public class GenreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long genreId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private GenreName genreName;

}
