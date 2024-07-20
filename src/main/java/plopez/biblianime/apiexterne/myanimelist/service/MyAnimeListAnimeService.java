package plopez.biblianime.apiexterne.myanimelist.service;

import plopez.biblianime.apiexterne.myanimelist.dto.*;
import plopez.biblianime.apiexterne.myanimelist.enumeration.Season;

import java.util.List;
import java.util.Map;

public interface MyAnimeListAnimeService {

    AnimeDTO getAnime(int id);

    List<AnimeSearchDTO> searchAnimes(String query, Integer n, Integer score, List<Integer> genre);

    List<TopAnimeDTO> getTopAnimes(Integer page, String category);

    List<AnimeRecommendationDTO> getAnimeRecommendations(Integer page);

    List<AnimeRecommendationDTO> getAnimeRecommendationsByAnime(Integer id);

    List<AnimeReviewDTO> getAnimeReviews(Integer page, Boolean spoilers, Boolean preliminary, List<String> includeTags, List<String> excludeTags);

    List<AnimeReviewDTO> getAnimeReviewsByAnime(Integer page, Boolean spoilers, Boolean preliminary, List<String> includeTags, List<String> excludeTags, String sort, int id);

    Map<String, List<AnimeSeasonDTO>> getSeasonalAnimes(int year, Season season);

    List<AnimeGenreDTO> getAnimeGenres();
}
