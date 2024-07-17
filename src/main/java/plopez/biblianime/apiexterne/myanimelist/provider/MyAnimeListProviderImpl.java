package plopez.biblianime.apiexterne.myanimelist.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
abstract class MyAnimeListProviderImpl implements MyAnimeListProvider {

    @Value("${api.myanimelist.base-url}")
    protected String BASE_URL;

    @Value("${api.myanimelist.key}")
    protected String X_RAPIDAPI_KEY;

    @Value("${api.myanimelist.host}")
    protected String X_RAPIDAPI_HOST;

    private final String TYPE = "";


    @Override
    public HttpResponse<String> get(int id) throws IOException, InterruptedException {
        return request(BASE_URL + TYPE + id, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> search(String query, Integer n, Integer score, Integer genre) throws IOException, InterruptedException {

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
    public HttpResponse<String> getTop(String category) throws IOException, InterruptedException {
        return request(BASE_URL + TYPE + "top/" + category, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> getRecommendations(Integer page) throws IOException, InterruptedException {
        if (page == null || page < 1) page = 1;
        return request(BASE_URL + "v2/" + TYPE + "recommendations" + "?p=" + page, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> getReviews(Integer page) throws IOException, InterruptedException {
        if (page == null || page < 1) page = 1;
        return request(BASE_URL + "v2/" + TYPE + "reviews" + "?p=" + page, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> getGenres() throws IOException, InterruptedException {
        return request(BASE_URL + "v2/" + TYPE + "genres", X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    /**
     * Envoie une requête HTTP GET à l'URL spécifiée avec les en-têtes requis.
     *
     * @param url             l'URL à laquelle envoyer la requête
     * @param x_rapidapi_key  la clé RapidAPI à inclure dans les en-têtes de la requête
     * @param x_rapidapi_host l'hôte RapidAPI à inclure dans les en-têtes de la requête
     * @return la réponse HTTP contenant le corps de la réponse sous forme de chaîne de caractères
     * @throws IOException          si une erreur d'E/S se produit lors de la requête
     * @throws InterruptedException si le thread est interrompu pendant l'attente de la réponse
     */
    HttpResponse<String> request(String url, String x_rapidapi_key, String x_rapidapi_host) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", x_rapidapi_key)
                .header("x-rapidapi-host", x_rapidapi_host)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
