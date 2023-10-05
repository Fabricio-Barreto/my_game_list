package com.plancto.mygamelist.models.game;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_GAME")
@Data
public class GameModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameId;
    @Column(nullable = false)
    private String title;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TB_GAMES_PLATFORM",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id"))
    @Column(nullable = false)
    private List<PlatformModel> platform;
    @ManyToOne(cascade = CascadeType.ALL)
    //@Column(nullable = false)
    @JoinColumn(name = "developer_id")
    private DeveloperModel developer;
    @ManyToOne(cascade = CascadeType.ALL)
    //@Column(nullable = false)
    @JoinColumn(name = "publisher_id")
    private PublisherModel publisher;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TB_GAMES_GENRE",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @Column(nullable = false)
    private List<GenreModel> genre;
    private Date ReleaseDate;
}
