package plopez.biblianime.myanimelist.anime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AnimeSeasonPropertieDTO {

    private AnimeSeasonPropertieStudioDTO studio;
    private String source;
    private AnimeSeasonPropertieThemeDTO theme;
    private AnimeSeasonPropertieDemographicDTO demographic;
}
