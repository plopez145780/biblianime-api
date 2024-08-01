package plopez.biblianime.apiexterne.myanimelistofficiel.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Component
public class MyAnimeListOfficielProviderImpl implements MyAnimeListOfficielProvider {

    Logger log = LoggerFactory.getLogger(MyAnimeListOfficielProviderImpl.class);
    @Value("${api.myanimelistofficiel.base-url}")
    private String baseUrl;
    @Value("${api.myanimelistofficiel.client-id}")
    private String clientId;

    @Override
    public HttpResponse<String> getAnimeList(String query, Integer limit, Integer offset, String fields) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("anime")
                .queryParamIfPresent("q", Optional.ofNullable(query))
                .queryParamIfPresent("limit", Optional.ofNullable(limit))
                .queryParamIfPresent("offset", Optional.ofNullable(offset))
                .queryParamIfPresent("fields", Optional.ofNullable(fields))
                .build()
                .toUri();
        return request(uri);
    }

    @Override
    public HttpResponse<String> getAnimeDetails(Integer animeId, String fields) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment("anime", animeId.toString())
                .queryParamIfPresent("fields", Optional.ofNullable(fields))
                .build()
                .toUri();
        return request(uri);
    }

    @Override
    public HttpResponse<String> getAnimeRanking(String rankingType, Integer limit, Integer offset, String fields) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment("anime", "ranking")
                .queryParamIfPresent("ranking_type", Optional.ofNullable(rankingType))
                .queryParamIfPresent("limit", Optional.ofNullable(limit))
                .queryParamIfPresent("offset", Optional.ofNullable(offset))
                .queryParamIfPresent("fields", Optional.ofNullable(fields))
                .build()
                .toUri();
        return request(uri);
    }

    @Override
    public HttpResponse<String> getSeasonalAnime(Integer year, String season, String sort, Integer limit, Integer offset, String fields) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment("anime", year.toString(), season)
                .queryParamIfPresent("sort", Optional.ofNullable(sort))
                .queryParamIfPresent("limit", Optional.ofNullable(limit))
                .queryParamIfPresent("offset", Optional.ofNullable(offset))
                .queryParamIfPresent("fields", Optional.ofNullable(fields))
                .build()
                .toUri();
        return request(uri);
    }

    HttpResponse<String> request(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("X-MAL-CLIENT-ID", clientId)
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
