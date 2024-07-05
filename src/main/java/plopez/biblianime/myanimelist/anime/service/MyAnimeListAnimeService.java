package plopez.biblianime.myanimelist.anime.service;

import plopez.biblianime.anime.entity.AnimeInformation;
import plopez.biblianime.anime.entity.Season;

import java.util.List;

public interface MyAnimeListAnimeService {

    List<AnimeInformation> getSeasonalAnimes(int year, Season season); //AnimeInformation
}
