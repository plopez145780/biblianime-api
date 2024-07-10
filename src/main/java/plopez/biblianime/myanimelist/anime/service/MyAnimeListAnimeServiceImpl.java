package plopez.biblianime.myanimelist.anime.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plopez.biblianime.myanimelist.anime.Season;
import plopez.biblianime.myanimelist.anime.dto.AnimeSeasonDTO;
import plopez.biblianime.myanimelist.anime.provider.MyAnimeListAnimeProvider;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyAnimeListAnimeServiceImpl implements MyAnimeListAnimeService {

    @Autowired
    private MyAnimeListAnimeProvider myAnimeListAnimeProvider;

    /**
     * Récupère les animes saisonniers de MyAnimeList en fonction de l'année et de la saison spécifiées.
     *
     * @param  year    l'année pour laquelle les animes saisonniers sont demandés
     * @param  season  la saison pour laquelle les animes saisonniers sont demandés
     * @return une map contenant une liste d'objets AnimeSeasonDTO catégorisés par type
     */
    @Override
    public Map<String, List<AnimeSeasonDTO>> getSeasonalAnimes(int year, Season season) {

        Map<String, List<AnimeSeasonDTO>> animesByType;

        try {
            // Récupère les animes saisonniers en JSON
            HttpResponse<String> seasonalAnimes = myAnimeListAnimeProvider.getSeasonalAnimes(year, season);

            // Deserialization
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<HashMap<String, List<AnimeSeasonDTO>>> typeRef = new TypeReference<>() {
            };

            animesByType = objectMapper.readValue(seasonalAnimes.body(), typeRef);

        } catch (Exception e) {
            e.printStackTrace();
            animesByType = new HashMap<>();
        }
        return animesByType;
    }
}
