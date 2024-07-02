package plopez.biblianime.myanimelist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class MyAnimeListAnimeImpl implements MyAnimeListAnime {

    @Value( "${myanimelist.base-url}" )
    private String BASE_URL;

    @Value( "${myanimelist.key}" )
    private String X_RAPIDAPI_KEY;

    @Value( "${myanimelist.host}" )
    private String X_RAPIDAPI_HOST;


    public MyAnimeListAnimeImpl() {}

    /**
     * Récupère les détails d'un anime à partir de l'API MyAnimeList en utilisant l'ID de l'anime fourni.
     *
     * @param  animeId  l'ID de l'anime à récupérer
     * @return          un objet HttpResponse contenant le corps de la réponse de l'API
     * @throws IOException          si une erreur d'E/S se produit pendant la requête
     * @throws InterruptedException si le thread est interrompu pendant l'attente de la réponse
     */
    public HttpResponse<String> getAnime (int animeId) throws IOException, InterruptedException {

        String url = BASE_URL + "anime/" + animeId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", X_RAPIDAPI_KEY)
                .header("x-rapidapi-host", X_RAPIDAPI_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
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

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", X_RAPIDAPI_KEY)
                .header("x-rapidapi-host", X_RAPIDAPI_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
    * Récupère les meilleurs anime du MyAnimeList API en fonction de la catégorie spécifiée.
    *
    * @param  category la catégorie d'anime à récupérer
    * @return          la réponse HTTP contenant les meilleurs anime de la catégorie spécifiée
    * @throws IOException          si une erreur I/O se produit lors de la requête API
    * @throws InterruptedException si le fil est interrompu pendant la réception de la réponse API
    */
    public HttpResponse<String> getTopAnimes (String category) throws IOException, InterruptedException {

        // exemple : https://myanimelist.p.rapidapi.com/anime/top/all
        String url = BASE_URL + "anime/top/" + category;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", X_RAPIDAPI_KEY)
                .header("x-rapidapi-host", X_RAPIDAPI_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getAnimeRecommendations () throws IOException, InterruptedException {

        // Page de résultats souhaitée. 1 par défaut.
        int page = 1;

        // exemple : https://myanimelist.p.rapidapi.com/v2/anime/recommendations?p=1
        StringBuilder url = new StringBuilder();
        url.append(BASE_URL).append("v2/anime/recommendations").append("?p=").append(page);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url.toString()))
                .header("x-rapidapi-key", X_RAPIDAPI_KEY)
                .header("x-rapidapi-host", X_RAPIDAPI_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getAnimeReviews () throws IOException, InterruptedException {

        // Page de résultats souhaitée. 1 par défaut.
        int page = 1;

        // exemple : https://myanimelist.p.rapidapi.com/v2/anime/reviews?p=1
        StringBuilder url = new StringBuilder();
        url.append(BASE_URL).append("v2/anime/reviews").append("?p=").append(page);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url.toString()))
                .header("x-rapidapi-key", X_RAPIDAPI_KEY)
                .header("x-rapidapi-host", X_RAPIDAPI_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getSeasonalAnimes () throws IOException, InterruptedException {

        int year = 2023;
        String season = "winter";

        //exemple : https://myanimelist.p.rapidapi.com/v2/anime/seasonal?year=2023&season=winter
        String url = BASE_URL + "v2/anime/seasonal" + "?year=" + year + "&season=" + season;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", X_RAPIDAPI_KEY)
                .header("x-rapidapi-host", X_RAPIDAPI_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getAnimeGenres () throws IOException, InterruptedException {

        //exemple : https://myanimelist.p.rapidapi.com/v2/anime/genres
        String url = BASE_URL + "v2/anime/genres";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", X_RAPIDAPI_KEY)
                .header("x-rapidapi-host", X_RAPIDAPI_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
