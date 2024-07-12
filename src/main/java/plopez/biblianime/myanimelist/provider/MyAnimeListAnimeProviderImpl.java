package plopez.biblianime.myanimelist.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import plopez.biblianime.myanimelist.Season;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class MyAnimeListAnimeProviderImpl implements MyAnimeListAnimeProvider {

    @Value("${api.myanimelist.base-url}")
    private String BASE_URL;

    @Value("${api.myanimelist.key}")
    private String X_RAPIDAPI_KEY;

    @Value("${api.myanimelist.host}")
    private String X_RAPIDAPI_HOST;


    public MyAnimeListAnimeProviderImpl() {
    }

    /**
     * Récupère les détails d'un anime à partir de l'API MyAnimeList en utilisant l'ID de l'anime fourni.
     *
     * @param id l'ID de l'anime à récupérer
     * @return un objet HttpResponse contenant le corps de la réponse de l'API
     * @throws IOException          si une erreur d'E/S se produit pendant la requête
     * @throws InterruptedException si le thread est interrompu pendant l'attente de la réponse
     */
    public HttpResponse<String> get(int id) throws IOException, InterruptedException {
        return request(BASE_URL + "anime/" + id);
    }

    /**
     * Recherche des animes en utilisant l'API MyAnimeList en fonction de la requête fournie.
     * Exemple d'URL : https://myanimelist.p.rapidapi.com/v2/anime/search?q=one%20piece&n=50&score=8&genre=1
     *
     * @param query la requête de recherche pour les animes
     * @param n     le nombre de résultats de recherche (minimum : 1, par défaut : 1, maximum : 50)
     * @param score le score minimum de l'anime (minimum : 0, par défaut : 0, maximum : 10)
     * @param genre l'ID du genre à rechercher (peut être obtenu en utilisant le point de terminaison Anime Genres)
     * @return un HttpResponse contenant le corps de la réponse de l'API
     * @throws IOException          si une erreur d'E/S se produit pendant la requête
     * @throws InterruptedException si le thread est interrompu pendant l'attente de la réponse
     */
    public HttpResponse<String> search(String query, Integer n, Integer score, Integer genre) throws IOException, InterruptedException {

        StringBuilder url = new StringBuilder(BASE_URL + "v2/anime/search");

        url.append("?q=").append(query);

        if (n == null || n < 1) n = 10;
        if (n > 50) n = 50;
        url.append("&n=").append(n);

        if (score != null && score >= 0 && score <= 10) url.append("&score=").append(score);

        if (genre != null) url.append("&genre=").append(genre);

        return request(url.toString());
    }

    /**
     * Récupère les meilleurs animés de MyAnimeList API en fonction de la catégorie spécifiée.
     * Exemple URL : https://myanimelist.p.rapidapi.com/anime/top/all
     *
     * @param category la catégorie d'anime à récupérer
     * @return la réponse HTTP contenant les meilleurs animés de la catégorie spécifiée
     * @throws IOException          si une erreur d'E/S se produit lors de la requête API
     * @throws InterruptedException si le thread est interrompu en attendant la réponse de l'API
     */
    public HttpResponse<String> getTop(String category) throws IOException, InterruptedException {
        return request(BASE_URL + "anime/top/" + category);
    }

    /**
     * Récupère les recommandations d'animes de l'API MyAnimeList en fonction de la page spécifiée.
     * Exemple URL : https://myanimelist.p.rapidapi.com/v2/anime/recommendations?p=1
     *
     * @param page le numéro de la page des résultats (par défaut 1)
     * @return la réponse HTTP contenant les animes recommandés pour la page spécifiée
     * @throws IOException          si une erreur d'E/S se produit lors de la requête API
     * @throws InterruptedException si le thread est interrompu en attendant la réponse de l'API
     */
    public HttpResponse<String> getRecommendations(Integer page) throws IOException, InterruptedException {
        if (page == null || page < 1) page = 1;
        return request(BASE_URL + "v2/anime/recommendations" + "?p=" + page);
    }

    /**
     * Récupère les reviews d'animes de l'API MyAnimeList en fonction de la page spécifiée.
     * Exemple URL : https://myanimelist.p.rapidapi.com/v2/anime/reviews?p=1
     *
     * @param page le numéro de la page des résultats (par défaut 1)
     * @return la réponse HTTP contenant les critiques d'animes pour la page spécifiée
     * @throws IOException          si une erreur d'E/S se produit lors de la requête API
     * @throws InterruptedException si le thread est interrompu en attendant la réponse de l'API
     */
    public HttpResponse<String> getReviews(Integer page) throws IOException, InterruptedException {
        if (page == null || page < 1) page = 1;
        return request(BASE_URL + "v2/anime/reviews" + "?p=" + page);
    }

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
    public HttpResponse<String> getSeasonal(int year, Season season) throws IOException, InterruptedException {
        return request(BASE_URL + "v2/anime/seasonal" + "?year=" + year + "&season=" + season);
    }

    /**
     * Récupère les genres d'animes disponibles.
     * Exemple URL : https://myanimelist.p.rapidapi.com/v2/anime/genres
     *
     * @return la réponse HTTP contenant les genres d'animes
     * @throws IOException          si une erreur d'entrée/sortie se produit lors de la requête HTTP
     * @throws InterruptedException si l'exécution est interrompue lors de l'attente de la réponse HTTP
     */
    public HttpResponse<String> getGenres() throws IOException, InterruptedException {
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
