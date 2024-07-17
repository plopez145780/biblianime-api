package plopez.biblianime.myanimelist.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plopez.biblianime.myanimelist.Season;
import plopez.biblianime.myanimelist.dto.AnimeSeasonDTO;
import plopez.biblianime.myanimelist.entity.ProviderExterne;
import plopez.biblianime.myanimelist.entity.RequeteExterne;
import plopez.biblianime.myanimelist.provider.MyAnimeListAnimeProvider;
import plopez.biblianime.myanimelist.repository.RequeteExterneRepository;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyAnimeListAnimeServiceImpl implements MyAnimeListAnimeService {

    private static final Long COUNT_MAX = 500L;
    @Autowired
    private MyAnimeListAnimeProvider myAnimeListAnimeProvider;

    @Autowired
    private RequeteExterneRepository requeteExterneRepository;

    /**
     * Récupère les animes saisonniers de MyAnimeList en fonction de l'année et de la saison spécifiées.
     *
     * @param  year    l'année pour laquelle les animes saisonniers sont demandés
     * @param  season  la saison pour laquelle les animes saisonniers sont demandés
     * @return une map contenant une liste d'objets AnimeSeasonDTO catégorisés par type
     */
    @Override
    public Map<String, List<AnimeSeasonDTO>> getSeasonalAnimes(int year, Season season) {

        Map<String, List<AnimeSeasonDTO>> animesByType = Map.of();

        try {

            /* compte que l'on est sous la limite de requetes possible */

            // Dates
            LocalDate today = LocalDate.now();
            LocalDateTime startDate = LocalDateTime.of(today.getYear(), today.getMonthValue(), 1, 0, 0, 0);
            LocalDateTime endDate = LocalDateTime.of(today.getYear(), today.getMonthValue(), today.lengthOfMonth(), 23, 59, 59);

            //requete COUNT
            Long count = requeteExterneRepository.countByProviderAndDateBetween(ProviderExterne.MYANIMELIST, startDate, endDate);
            if (count <= ProviderExterne.MYANIMELIST.getCount()) {
                // execute l'appel

            // Récupère les animes saisonniers en JSON
            HttpResponse<String> seasonalAnimes = myAnimeListAnimeProvider.getSeasonal(year, season);

            // Deserialization
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<HashMap<String, List<AnimeSeasonDTO>>> typeRef = new TypeReference<>() {
            };

            animesByType = objectMapper.readValue(seasonalAnimes.body(), typeRef);

                String url = seasonalAnimes.uri().toString();


                // Enregistre l'URL de l'appel
                RequeteExterne requeteExterne = new RequeteExterne();
                requeteExterne.setProvider(ProviderExterne.MYANIMELIST);
                requeteExterne.setDate(LocalDateTime.now());
                requeteExterne.setUrl(url);
                requeteExterneRepository.save(requeteExterne);

            }


        } catch (Exception e) {
            e.printStackTrace();
            animesByType = new HashMap<>();
        }
        return animesByType;
    }
}
