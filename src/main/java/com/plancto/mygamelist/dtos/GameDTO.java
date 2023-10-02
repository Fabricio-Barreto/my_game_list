package com.plancto.mygamelist.dtos;

import com.plancto.mygamelist.models.game.DeveloperModel;
import com.plancto.mygamelist.models.game.GenreModel;
import com.plancto.mygamelist.models.game.PlatformModel;
import com.plancto.mygamelist.models.game.PublisherModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private Long gameId;
    private String title;
    private List<PlatformModel> platformModel;
    private DeveloperModel developerModel;
    private PublisherModel publisherModel;
    private List<GenreModel> genreModel;
    private Date ReleaseDate;
}
