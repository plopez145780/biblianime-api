package plopez.biblianime.myanimelist.anime.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plopez.biblianime.anime.entity.AnimeInformation;
import plopez.biblianime.anime.entity.Season;
import plopez.biblianime.myanimelist.anime.provider.MyAnimeListAnimeProvider;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyAnimeListAnimeServiceImpl implements MyAnimeListAnimeService {

    @Autowired
    private MyAnimeListAnimeProvider myAnimeListAnimeProvider;

    /**
     * Récupère une liste d'informations sur les animés saisonniers en fonction de l'année et de la saison donnée.
     *
     * @param year   l'année de la saison
     * @param season la saison de l'anime
     * @return une liste d'objets AnimeInformation représentant les animés saisonniers
     */
    @Override
    public List<AnimeInformation> getSeasonalAnimes(int year, Season season) {

        List<AnimeInformation> animes;

        try {
            HttpResponse<String> seasonalAnimes = myAnimeListAnimeProvider.getSeasonalAnimes(year, season);
            String body = seasonalAnimes.body();

            ObjectMapper objectMapper = new ObjectMapper();
            AnimeInformation[] todos = objectMapper.readValue(body, AnimeInformation[].class);
            animes = List.of(todos);

        } catch (IOException e) {
            e.printStackTrace();
            animes = new ArrayList<>();

        } catch (InterruptedException e) {
            e.printStackTrace();
            animes = new ArrayList<>();

        } catch (Exception e) {
            e.printStackTrace();
            animes = new ArrayList<>();
        }


        return animes;
    }
}
