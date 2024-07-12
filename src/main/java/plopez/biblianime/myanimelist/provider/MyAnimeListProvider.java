package plopez.biblianime.myanimelist.provider;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface MyAnimeListProvider {

    HttpResponse<String> get(int id) throws IOException, InterruptedException;

    HttpResponse<String> search(String query, Integer n, Integer score, Integer genre) throws IOException, InterruptedException;

    HttpResponse<String> getTop(String category) throws IOException, InterruptedException;

    HttpResponse<String> getRecommendations(Integer page) throws IOException, InterruptedException;

    HttpResponse<String> getReviews(Integer page) throws IOException, InterruptedException;

    HttpResponse<String> getGenres() throws IOException, InterruptedException;

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
    default HttpResponse<String> request(String url, String x_rapidapi_key, String x_rapidapi_host) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", x_rapidapi_key)
                .header("x-rapidapi-host", x_rapidapi_host)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
