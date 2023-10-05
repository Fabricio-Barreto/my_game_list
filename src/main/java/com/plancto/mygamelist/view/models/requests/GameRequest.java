package com.plancto.mygamelist.view.models.requests;

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
public class GameRequest {
    private Long gameId;
    private String title;
    private List<PlatformModel> platform;
    private DeveloperModel developer;
    private PublisherModel publisher;
    private List<GenreModel> genre;
    private Date ReleaseDate;
}
