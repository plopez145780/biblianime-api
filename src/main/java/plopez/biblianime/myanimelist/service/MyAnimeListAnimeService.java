package plopez.biblianime.myanimelist.service;

import plopez.biblianime.myanimelist.Season;
import plopez.biblianime.myanimelist.dto.AnimeSeasonDTO;

import java.util.List;
import java.util.Map;

public interface MyAnimeListAnimeService {

    Map<String, List<AnimeSeasonDTO>> getSeasonalAnimes(int year, Season season);
}
