package plopez.biblianime.anime.mapper;

import plopez.biblianime.anime.dto.AnimeShortDTO;
import plopez.biblianime.anime.entity.AnimeInformation;

public class AnimeMapper {

    public static AnimeShortDTO toAnimeShortDTO(AnimeInformation animeInformation) {
        return new AnimeShortDTO(
                animeInformation.getTitle(),
                animeInformation.getDescription(),
                animeInformation.getPictureUrl(),
                animeInformation.getMyanimelistUrl(),
                animeInformation.getMyanimelistId()
        );
    }
}
