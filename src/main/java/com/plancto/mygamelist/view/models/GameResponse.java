package com.plancto.mygamelist.view.models;

import com.plancto.mygamelist.models.game.DeveloperModel;
import com.plancto.mygamelist.models.game.GenreModel;
import com.plancto.mygamelist.models.game.PlatformModel;
import com.plancto.mygamelist.models.game.PublisherModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResponse extends RepresentationModel<UserResponse> {
    private Long gameId;
    private String title;
    private List<PlatformModel> platformModel;
    private DeveloperModel developerModel;
    private PublisherModel publisherModel;
    private List<GenreModel> genreModel;
    private Date ReleaseDate;
}
