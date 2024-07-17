package plopez.biblianime.anime.mapper;

import plopez.biblianime.anime.dto.AnimeCardDTO;
import plopez.biblianime.apiexterne.myanimelist.dto.AnimeSeasonDTO;
import plopez.biblianime.apiexterne.myanimelist.dto.GenreDTO;

import java.util.List;

public class AnimeMapper {

    public static AnimeCardDTO toAnimeCardDTO(AnimeSeasonDTO animeSeasonDTO) {

        List<String> genres = animeSeasonDTO.getGenres().stream()
                .map(GenreDTO::getName)
                .toList();


        int id = Integer.parseInt(animeSeasonDTO.getUrl().split("/")[4]);

        return new AnimeCardDTO(
                animeSeasonDTO.getTitle(),
                animeSeasonDTO.getUrl(),
                genres,
                animeSeasonDTO.getSynopsis(),
                animeSeasonDTO.getImageUrl(),
                animeSeasonDTO.getEpisodes(),
                id
        );
    }
}
