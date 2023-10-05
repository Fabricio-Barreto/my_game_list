package com.plancto.mygamelist.view.models.responses;

import com.plancto.mygamelist.enums.GenreName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreResponse extends RepresentationModel<UserResponse>  {
    private Long genreId;
    private GenreName genreName;
}
