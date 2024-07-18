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

@Service
abstract class MyAnimeListProviderImpl implements MyAnimeListProvider {

    Logger log = LoggerFactory.getLogger(MyAnimeListProviderImpl.class);

    @Value("${api.myanimelist.base-url}")
    protected String BASE_URL;

    @Value("${api.myanimelist.key}")
    protected String X_RAPIDAPI_KEY;

    @Value("${api.myanimelist.host}")
    protected String X_RAPIDAPI_HOST;

    protected String TYPE = "";


    @Override
    public HttpResponse<String> get(int id) {
        return request(BASE_URL + TYPE + id, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> search(String query, Integer n, Integer score, Integer genre) {

        StringBuilder url = new StringBuilder(BASE_URL + "v2/" + TYPE + "search");

        url.append("?q=").append(query);

        if (n == null || n < 1) n = 10;
        if (n > 50) n = 50;
        url.append("&n=").append(n);

        if (score != null && score >= 0 && score <= 10) url.append("&score=").append(score);

        if (genre != null) url.append("&genre=").append(genre);

        return request(url.toString(), X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> getTop(String category) {
        return request(BASE_URL + TYPE + "top/" + category, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> getRecommendations(Integer page) {
        if (page == null || page < 1) page = 1;
        return request(BASE_URL + "v2/" + TYPE + "recommendations" + "?p=" + page, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> getReviews(Integer page) {
        if (page == null || page < 1) page = 1;
        return request(BASE_URL + "v2/" + TYPE + "reviews" + "?p=" + page, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> getGenres() {
        return request(BASE_URL + "v2/" + TYPE + "genres", X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    /**
     * Envoie une requête HTTP GET à l'URL spécifiée avec les en-têtes requis.
     *
     * @param url             l'URL à laquelle envoyer la requête
     * @param x_rapidapi_key  la clé RapidAPI à inclure dans les en-têtes de la requête
     * @param x_rapidapi_host l'hôte RapidAPI à inclure dans les en-têtes de la requête
     * @return la réponse HTTP contenant le corps de la réponse sous forme de chaîne de caractères
     */
    HttpResponse<String> request(String url, String x_rapidapi_key, String x_rapidapi_host) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", x_rapidapi_key)
                .header("x-rapidapi-host", x_rapidapi_host)
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
