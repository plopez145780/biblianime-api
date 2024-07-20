package plopez.biblianime.apiexterne.myanimelist.provider;

import org.springframework.stereotype.Service;
import plopez.biblianime.apiexterne.myanimelist.enumeration.Season;
import plopez.biblianime.apiexterne.requeteexternelog.ProviderExterne;
import plopez.biblianime.apiexterne.requeteexternelog.RequeteExterneLog;
import plopez.biblianime.apiexterne.requeteexternelog.RequeteExterneProvider;

import java.net.http.HttpResponse;

@RequeteExterneProvider(provider = ProviderExterne.MYANIMELIST)
@Service
public class MyAnimeListAnimeProviderImpl extends MyAnimeListProviderImpl implements MyAnimeListAnimeProvider {

    protected String TYPE = "anime/";

    /**
     * Récupère les animes saisonniers pour une année et une saison spécifiques.
     * Exemple URL : https://myanimelist.p.rapidapi.com/v2/anime/seasonal?year=2023&season=winter
     *
     * @param year   l'année pour laquelle les animes saisonniers sont demandés
     * @param season la saison pour laquelle les animes saisonniers sont demandés
     * @return la réponse HTTP contenant les animes saisonniers
     */
    @RequeteExterneLog
    public HttpResponse<String> getSeasonal(int year, Season season) {
        return request(BASE_URL + "v2/" + TYPE + "seasonal" + "?year=" + year + "&season=" + season);
    }
}
