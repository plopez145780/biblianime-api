package plopez.biblianime.apiexterne.myanimelist.service;

import plopez.biblianime.apiexterne.myanimelist.Season;
import plopez.biblianime.apiexterne.myanimelist.dto.AnimeSeasonDTO;

import java.util.List;
import java.util.Map;

public interface MyAnimeListAnimeService {

    Map<String, List<AnimeSeasonDTO>> getSeasonalAnimes(int year, Season season);
}
