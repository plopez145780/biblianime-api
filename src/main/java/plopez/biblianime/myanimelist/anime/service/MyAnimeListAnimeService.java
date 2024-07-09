package plopez.biblianime.myanimelist.anime.service;

import plopez.biblianime.anime.entity.Season;
import plopez.biblianime.myanimelist.anime.dto.AnimeSeasonDTO;

import java.util.List;
import java.util.Map;

public interface MyAnimeListAnimeService {

    Map<String, List<AnimeSeasonDTO>> getSeasonalAnimes(int year, Season season);
}
