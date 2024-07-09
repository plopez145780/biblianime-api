package plopez.biblianime.myanimelist.anime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AnimeSeasonDTO {

    private String title;
    private String type;
    private String url;
    private List<AnimeSeasonGenreDTO> genres;
    private String image_url;
    private int score;
    private int members;
    private String synopsis;
    private AnimeSeasonDateDTO date;
    int episodes;
    int duration;
    private AnimeSeasonPropertieDTO properties;
}

