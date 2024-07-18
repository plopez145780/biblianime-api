package plopez.biblianime.apiexterne.myanimelist.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import plopez.biblianime.apiexterne.myanimelist.Season;
import plopez.biblianime.apiexterne.myanimelist.dto.AnimeSeasonDTO;
import plopez.biblianime.apiexterne.myanimelist.provider.MyAnimeListAnimeProvider;
import plopez.biblianime.apiexterne.requeteexternelog.RequeteExterneRepository;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyAnimeListAnimeServiceImpl implements MyAnimeListAnimeService {

    Logger log = LoggerFactory.getLogger(MyAnimeListAnimeServiceImpl.class);

    @Autowired
    private MyAnimeListAnimeProvider myAnimeListAnimeProvider;

    @Autowired
    private RequeteExterneRepository requeteExterneRepository;

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
}
