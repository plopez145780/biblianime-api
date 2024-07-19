package plopez.biblianime.apiexterne.myanimelist.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import plopez.biblianime.apiexterne.myanimelist.Season;
import plopez.biblianime.apiexterne.requeteexternelog.ProviderExterne;
import plopez.biblianime.apiexterne.requeteexternelog.RequeteExterneLog;
import plopez.biblianime.apiexterne.requeteexternelog.RequeteExterneProvider;

import java.io.IOException;
import java.net.http.HttpResponse;

@RequeteExterneProvider(provider = ProviderExterne.MYANIMELIST)
@Service
public class MyAnimeListAnimeProviderImpl extends MyAnimeListProviderImpl implements MyAnimeListAnimeProvider {

    private static final Logger log = LoggerFactory.getLogger(MyAnimeListAnimeProviderImpl.class);

    protected String TYPE = "anime/";


    /**
     * Récupère les animes saisonniers pour une année et une saison spécifiques.
     * Exemple URL : https://myanimelist.p.rapidapi.com/v2/anime/seasonal?year=2023&season=winter
     *
     * @param year   l'année pour laquelle les animes saisonniers sont demandés
     * @param season la saison pour laquelle les animes saisonniers sont demandés
     * @return la réponse HTTP contenant les animes saisonniers
     * @throws IOException          si une erreur d'entrée/sortie se produit lors de la requête HTTP
     * @throws InterruptedException si l'exécution est interrompue lors de l'attente de la réponse HTTP
     */
    @RequeteExterneLog
    public HttpResponse<String> getSeasonal(int year, Season season) {
        log.info("Appel API externe myAnimeList pour les animes saisonniers (année {} / saison {})", year, season);
        return request(BASE_URL + "v2/" + TYPE + "seasonal" + "?year=" + year + "&season=" + season, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }
}