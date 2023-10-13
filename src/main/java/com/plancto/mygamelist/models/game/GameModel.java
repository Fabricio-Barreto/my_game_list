package com.plancto.mygamelist.models.game;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_GAME")
public class GameModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameId;
    @Column(nullable = false)
    private String title;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "TB_GAMES_PLATFORM",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id"))
    @Column(nullable = false)
    private List<PlatformModel> platform;
    @ManyToOne(cascade = CascadeType.MERGE)
    //@Column(nullable = false)
    @JoinColumn(name = "developer_id")
    private DeveloperModel developer;
    @ManyToOne(cascade = CascadeType.MERGE)
    //@Column(nullable = false)
    @JoinColumn(name = "publisher_id")
    private PublisherModel publisher;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "TB_GAMES_GENRE",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @Column(nullable = false)
    private List<GenreModel> genre;
    private Date ReleaseDate;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PlatformModel> getPlatform() {
        return platform;
    }

    public void setPlatform(List<PlatformModel> platform) {
        this.platform = platform;
    }

    public DeveloperModel getDeveloper() {
        return developer;
    }

    public void setDeveloper(DeveloperModel developer) {
        this.developer = developer;
    }

    public PublisherModel getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherModel publisher) {
        this.publisher = publisher;
    }

    public List<GenreModel> getGenre() {
        return genre;
    }

    public void setGenre(List<GenreModel> genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        ReleaseDate = releaseDate;
    }
}
