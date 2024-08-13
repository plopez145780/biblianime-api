package plopez.biblianime.anime.mapper;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import plopez.biblianime.anime.dto.AnimeCardDTO;
import plopez.biblianime.anime.dto.AnimeDetailDTO;
import plopez.biblianime.anime.entity.Anime2;
import plopez.biblianime.apiexterne.myanimelistofficiel.dto.AnimeDTO;
import plopez.biblianime.apiexterne.myanimelistofficiel.dto.GenreDTO;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
public class AnimeMapper {

    public AnimeCardDTO toAnimeCardDTO(@NotNull AnimeDTO animeDTO) {

        List<String> genres = Optional.ofNullable(animeDTO.getGenres()).orElse(List.of()).stream()
                .map(GenreDTO::getName)
                .collect(toList());

        String url = "https://www.myanimelist.net/anime/" + animeDTO.getId();

        return new AnimeCardDTO(
                animeDTO.getId(),
                animeDTO.getTitle(),
                genres,
                animeDTO.getSynopsis(),
                animeDTO.getSynopsis(),
                animeDTO.getMainPicture().getMedium(),
                animeDTO.getNumEpisodes(),
                url
        );
    }

    public AnimeDetailDTO toAnimeDetailDTO(Anime2 anime2) {
        return null;
    }
}
