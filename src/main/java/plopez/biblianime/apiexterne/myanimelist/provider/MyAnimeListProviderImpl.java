package plopez.biblianime.apiexterne.myanimelist.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Deprecated
@Service
abstract class MyAnimeListProviderImpl implements MyAnimeListProvider {

    @Value("${api.myanimelist.base-url}")
    protected String BASE_URL;

    @Value("${api.myanimelist.key}")
    protected String X_RAPIDAPI_KEY;

    @Value("${api.myanimelist.host}")
    protected String X_RAPIDAPI_HOST;

    protected String TYPE = "";

    Logger log = LoggerFactory.getLogger(MyAnimeListProviderImpl.class);

    /**
     * Récupère la réponse HTTP pour l'ID spécifié.
     *
     * @param id l'ID de la ressource à récupérer
     * @return la réponse HTTP sous forme de chaîne de caractères
     */
    @Override
    public HttpResponse<String> get(int id) {
        return request(BASE_URL + TYPE + id);
    }

    /**
     * Recherche de ressource à partir de leur nom, score, et genre
     *
     * @param query requête de recherche utilisée par la recherche sur https://myanimelist.net/.
     * @param n     nombre de résultats (Minimum : 1 / Défaut : 1 / Maximum : 50)
     * @param score Score minimum de l'anime (Minimum : 0  / Tous les animes : 0 / Par défaut : 0 / Maximum : 10)
     * @param genre ID du genre que vous souhaitez rechercher.
     *              Il peut être obtenu en utilisant le point de terminaison Anime Genres.
     *              Vous pouvez filtrer plusieurs genres en les séparant par ','
     * @return la recherche de l'API MyAnimeList sous forme de chaîne de caractères
     */
    @Override
    public HttpResponse<String> search(String query, Integer n, Integer score, String genre) {
        StringBuilder url = new StringBuilder(BASE_URL + "v2/" + TYPE + "search");
        url.append("?q=").append(query);
        url.append("&n=").append(n);
        if (score != null) url.append("&score=").append(score);
        if (genre != null) url.append("&genre=").append(genre);
        return request(url.toString());
    }

    /*@Override
    public HttpResponse<String> getTop(Integer page, String category) {
        return request(BASE_URL + TYPE + "top/" + category);
    }*/

    /*@Override
    public HttpResponse<String> getRecommendations(Integer page) {
        if (page == null || page < 1) page = 1;
        return request(BASE_URL + "v2/" + TYPE + "recommendations" + "?p=" + page);
    }*/

    /*@Override
    public HttpResponse<String> getRecommendationsByRessource(int id) {
        return request(BASE_URL + "v2/" + TYPE + "recommendations/" + id);
    }*/

    /**
     * Récupère les critiques de l'API MyAnimeList pour une page spécifique.
     *
     * @param page le numéro de page à partir duquel récupérer les critiques, si nul ou inférieur à 1, par défaut 1
     * @return la réponse HTTP contenant les critiques sous forme de chaîne de caractères
     */
    /*@Override
    public HttpResponse<String> getReviews(Integer page, Boolean spoilers, Boolean preliminary, String includeTags, String excludeTags) {
        if (page == null || page < 1) page = 1;
        return request(BASE_URL + "v2/" + TYPE + "reviews" + "?p=" + page);
    }*/

    /*@Override
    public HttpResponse<String> getReviewsByRessources(Integer page, Boolean spoilers, Boolean preliminary, String includeTags, String excludeTags, int id) {

        // https://myanimelist.p.rapidapi.com/v2/anime/reviews/52991?p=1&spoilers=false&preliminary=true&include_ta

        StringBuilder url = new StringBuilder(BASE_URL + "v2/" + TYPE + "reviews/" + id);
        if (page != null) url.append("?p=").append(page);
        if (spoilers != null) url.append("&spoilers=").append(spoilers);
        if (preliminary != null) url.append("&preliminary=").append(preliminary);
        if (includeTags != null) url.append("&include_tags=").append(includeTags);
        if (excludeTags != null) url.append("&exclude_tags=").append(excludeTags);

        return request(url.toString());
    }*/

    /**
     * Récupère les genres.
     *
     * @return la réponse HTTP contenant les genres sous forme de chaîne de caractères
     */
    @Override
    public HttpResponse<String> getGenres() {
        return request(BASE_URL + "v2/" + TYPE + "genres");
    }

    /**
     * Envoie une requête HTTP GET à l'URL spécifiée avec les en-têtes requis.
     *
     * @param url l'URL à laquelle envoyer la requête
     * @return la réponse HTTP contenant le corps de la réponse sous forme de chaîne de caractères
     */
    HttpResponse<String> request(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", X_RAPIDAPI_KEY)
                .header("x-rapidapi-host", X_RAPIDAPI_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            //si une erreur d'E/S se produit lors de la requête
            log.error("IOException lors de la requête HTTP : {}", e.getMessage());
        } catch (InterruptedException e) {
            //si le thread est interrompu pendant l'attente de la réponse
            log.error("InterruptedException lors de la requête HTTP : {}", e.getMessage());
        }
        return response;
    }
}
