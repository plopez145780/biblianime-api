package plopez.biblianime.apiexterne.myanimelist.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import plopez.biblianime.apiexterne.myanimelist.dto.*;
import plopez.biblianime.apiexterne.myanimelist.enumeration.Season;
import plopez.biblianime.apiexterne.myanimelist.provider.MyAnimeListAnimeProvider;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Deprecated
@Service
public class MyAnimeListAnimeServiceImpl implements MyAnimeListAnimeService {

    Logger log = LoggerFactory.getLogger(MyAnimeListAnimeServiceImpl.class);

    @Autowired
    private MyAnimeListAnimeProvider myAnimeListAnimeProvider;

    @Override
    public AnimeDTO getAnime(int id) {

        AnimeDTO anime;

        HttpResponse<String> httpResponse = myAnimeListAnimeProvider.get(id);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            anime = objectMapper.readValue(httpResponse.body(), AnimeDTO.class);
        } catch (Exception e) {
            log.error("Deserialization error: {}", e.getMessage());
            anime = new AnimeDTO();
        }
        return anime;
    }

    @Override
    public List<AnimeSearchDTO> searchAnimes(String query, Integer n, Integer score, List<Integer> genre) {


        // Default values
        if (n == null || n < 1) n = 10;
        if (n > 50) n = 50;

        if (score < 0 || score > 10) score = null;

        String genreSting = genre.stream()
                .distinct()
                .map(String::valueOf)
                .collect(Collectors.joining(","));


        HttpResponse<String> httpResponse = myAnimeListAnimeProvider.search(query, n, score, genreSting);

        return null;
    }

    @Override
    public List<TopAnimeDTO> getTopAnimes(Integer page, String category) {
        return List.of();
    }

    @Override
    public List<AnimeRecommendationDTO> getAnimeRecommendations(Integer page) {
        return List.of();
    }

    @Override
    public List<AnimeRecommendationDTO> getAnimeRecommendationsByAnime(Integer page) {
        return List.of();
    }

    @Override
    public List<AnimeReviewDTO> getAnimeReviews(Integer page, Boolean spoilers, Boolean preliminary, List<String> includeTags, List<String> excludeTags) {
        return List.of();
    }

    @Override
    public List<AnimeReviewDTO> getAnimeReviewsByAnime(Integer page, Boolean spoilers, Boolean preliminary, List<String> includeTags, List<String> excludeTags, String sort, int id) {
        return List.of();
    }


    /**
     * Récupère les animes saisonniers de MyAnimeList en fonction de l'année et de la saison spécifiées.
     *
     * @param year   l'année pour laquelle les animes saisonniers sont demandés
     * @param season la saison pour laquelle les animes saisonniers sont demandés
     * @return une map contenant une liste d'objets AnimeSeasonDTO catégorisés par type
     */
    @Cacheable(
            value = "seasonalAnimesCache",
            key = "{#year, #season}")
    @Override
    public Map<String, List<AnimeSeasonDTO>> getSeasonalAnimes(int year, Season season) {

        Map<String, List<AnimeSeasonDTO>> animesByType;

        // Récupère les animes saisonniers en JSON
        HttpResponse<String> seasonalAnimes = myAnimeListAnimeProvider.getSeasonal(year, season);

        // Deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HashMap<String, List<AnimeSeasonDTO>>> typeRef = new TypeReference<>() {
        };
        try {
            animesByType = objectMapper.readValue(seasonalAnimes.body(), typeRef);
        } catch (Exception e) {
            log.error("Deserialization error: {}", e.getMessage());
            animesByType = new HashMap<>();
        }
        return animesByType;
    }

    @Override
    public List<AnimeGenreDTO> getAnimeGenres() {
        List<AnimeGenreDTO> animeGenres = null;
        HttpResponse<String> httpResponse = myAnimeListAnimeProvider.getGenres();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            animeGenres = objectMapper.readValue(httpResponse.body(), new TypeReference<List<AnimeGenreDTO>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Deserialization error: {}", e.getMessage());
        }
        return animeGenres;
    }
}
