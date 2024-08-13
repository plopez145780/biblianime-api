package plopez.biblianime.apiexterne.myanimelist.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Deprecated
@Getter
@Setter
@RequiredArgsConstructor
public class AnimeGenreDTO {

    private int id;
    private String title;
    private int amount;

}
