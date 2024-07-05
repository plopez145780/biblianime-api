package plopez.biblianime.myanimelist.anime.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import plopez.biblianime.anime.entity.Season;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class MyAnimeListAnimeProviderImpl implements MyAnimeListAnimeProvider {

    @Value( "${myanimelist.base-url}" )
    private String BASE_URL;

    @Value( "${myanimelist.key}" )
    private String X_RAPIDAPI_KEY;

    @Value( "${myanimelist.host}" )
    private String X_RAPIDAPI_HOST;


    public MyAnimeListAnimeProviderImpl() {
    }

    /**
     * Récupère les détails d'un anime à partir de l'API MyAnimeList en utilisant l'ID de l'anime fourni.
     *
     * @param  animeId  l'ID de l'anime à récupérer
     * @return          un objet HttpResponse contenant le corps de la réponse de l'API
     * @throws IOException          si une erreur d'E/S se produit pendant la requête
     * @throws InterruptedException si le thread est interrompu pendant l'attente de la réponse
     */
    public HttpResponse<String> getAnime (int animeId) throws IOException, InterruptedException {

        return request(BASE_URL + "anime/" + animeId);
    }


    public HttpResponse<String> searchAnimes () throws IOException, InterruptedException {

        // Requête de recherche utilisée par la recherche sur https://myanimelist.net/.
        String query = "";
        //Nombre de résultats : Minimum : 1 Défaut : 1 Maximum : 50
        int n = 50;
        //Score minimum de l'anime :: Minimum : 0 / Tous les animes : 0 /  Par défaut : 0 / Maximum : 10
        int score = 8;
        //ID du genre que vous souhaitez rechercher. Peut être obtenu en utilisant le point de terminaison Anime Genres.
        int genre = 1;

        // exemple : https://myanimelist.p.rapidapi.com/v2/anime/search?q=one%20piece&n=50&score=8&genre=1
        String url = BASE_URL + "v2/anime/search" +
                "?q=" + query +
                "&n=" + n +
                "&score=" + score +
                "&genre=" + genre;

        return request(url);
    }

    /**
     * Récupère les meilleurs animés de MyAnimeList API en fonction de la catégorie spécifiée.
    *
    * @param  category la catégorie d'anime à récupérer
     * @return la réponse HTTP contenant les meilleurs animés de la catégorie spécifiée
    * @throws IOException          si une erreur I/O se produit lors de la requête API
    * @throws InterruptedException si le fil est interrompu pendant la réception de la réponse API
    */
    public HttpResponse<String> getTopAnimes (String category) throws IOException, InterruptedException {

        // exemple : https://myanimelist.p.rapidapi.com/anime/top/all
        return request(BASE_URL + "anime/top/" + category);
    }

    public HttpResponse<String> getAnimeRecommendations(Integer page) throws IOException, InterruptedException {

        // Page de résultats souhaitée. 1 par défaut.
        if (page == null) {
            page = 1;
        }

        // exemple : https://myanimelist.p.rapidapi.com/v2/anime/recommendations?p=1
        return request(BASE_URL + "v2/anime/recommendations" + "?p=" + page);
    }

    public HttpResponse<String> getAnimeReviews(Integer page) throws IOException, InterruptedException {

        // Page de résultats souhaitée. 1 par défaut.
        if (page == null) {
            page = 1;
        }

        // exemple : https://myanimelist.p.rapidapi.com/v2/anime/reviews?p=1
        return request(BASE_URL + "v2/anime/reviews" + "?p=" + page);
    }

    public HttpResponse<String> getSeasonalAnimes(int year, Season season) throws IOException, InterruptedException {
        //exemple : https://myanimelist.p.rapidapi.com/v2/anime/seasonal?year=2023&season=winter
        return request(BASE_URL + "v2/anime/seasonal" + "?year=" + year + "&season=" + season);
    }

    public HttpResponse<String> getAnimeGenres () throws IOException, InterruptedException {
        //exemple : https://myanimelist.p.rapidapi.com/v2/anime/genres
        return request(BASE_URL + "v2/anime/genres");
    }

    /**
     * Envoie une requête HTTP GET à l'URL spécifiée avec les en-têtes nécessaires.
     *
     * @param url L'URL vers laquelle envoyer la requête
     * @return Une réponse HttpResponse contenant le corps de la réponse sous forme de chaîne de caractères
     * @throws IOException          si une erreur d'E/S se produit lors de la requête
     * @throws InterruptedException si le thread est interrompu pendant l'attente de la réponse
     */
    private HttpResponse<String> request(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", X_RAPIDAPI_KEY)
                .header("x-rapidapi-host", X_RAPIDAPI_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
