package com.plancto.mygamelist.view.models.responses;

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
    private List<PlatformModel> platform;
    private DeveloperModel developer;
    private PublisherModel publisher;
    private List<GenreModel> genre;
    private Date ReleaseDate;
}
