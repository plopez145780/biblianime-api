package plopez.biblianime.apiexterne.myanimelistofficiel.service;

import jakarta.validation.constraints.NotNull;
import plopez.biblianime.apiexterne.myanimelistofficiel.dto.AnimeDTO;

import java.util.List;

public interface MyAnimeListOfficielService {

    List<AnimeDTO> getAnimeList(String query, Integer limit, Integer offset, String fields);

    AnimeDTO getAnimeDetails(@NotNull Integer animeId, String fields);

    List<AnimeDTO> getAnimeRanking(@NotNull String rankingType, Integer limit, Integer offset, String fields);

    List<AnimeDTO> getSeasonalAnime(@NotNull Integer year, @NotNull String season, String sort, Integer limit, Integer offset, String fields);

}
