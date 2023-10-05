package com.plancto.mygamelist.dtos;

import com.plancto.mygamelist.enums.GenreName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {
    private Long genreId;
    private GenreName genreName;
}
