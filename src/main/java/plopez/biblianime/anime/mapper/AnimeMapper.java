package plopez.biblianime.anime.mapper;

import org.springframework.stereotype.Component;
import plopez.biblianime.anime.dto.AnimeCardDTO;
import plopez.biblianime.anime.dto.AnimeDetailDTO;
import plopez.biblianime.anime.entity.Anime2;
import plopez.biblianime.apiexterne.myanimelist.dto.AnimeSeasonDTO;
import plopez.biblianime.apiexterne.myanimelist.dto.GenreDTO;

import java.util.List;

@Component
public class AnimeMapper {

    public AnimeCardDTO toAnimeCardDTO(AnimeSeasonDTO animeSeasonDTO) {

        List<String> genres = animeSeasonDTO.getGenres().stream()
                .map(GenreDTO::getName)
                .toList();


        int id = Integer.parseInt(animeSeasonDTO.getUrl().split("/")[4]);

        return new AnimeCardDTO(
                id,
                animeSeasonDTO.getTitle(),
                genres,
                animeSeasonDTO.getSynopsis(),
                animeSeasonDTO.getSynopsis(),
                animeSeasonDTO.getImageUrl(),
                animeSeasonDTO.getEpisodes(),
                animeSeasonDTO.getUrl()
        );
    }

    public AnimeDetailDTO toAnimeDetailDTO(Anime2 anime2) {

        AnimeDetailDTO animeDetailDTO = new AnimeDetailDTO();

        animeDetailDTO.setTitle(anime2.getTitle());
        animeDetailDTO.setAlternativeTitle(anime2.getAlternativeTitle());
        animeDetailDTO.setEnglishTitle(anime2.getEnglishTitle());
        animeDetailDTO.setJapTitle(anime2.getJapTitle());
        animeDetailDTO.setUrl(anime2.getUrl());
        animeDetailDTO.setGenres(anime2.getGenres());
        animeDetailDTO.setDescription(anime2.getDescription());
        animeDetailDTO.setImageUrl(anime2.getImageUrl());
        animeDetailDTO.setTotalEpisodes(anime2.getTotalEpisodes());
        animeDetailDTO.setMyanimelistId(anime2.getMyanimelistId());

        return animeDetailDTO;
    }
}
