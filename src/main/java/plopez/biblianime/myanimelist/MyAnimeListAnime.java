package plopez.biblianime.myanimelist;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface MyAnimeListAnime {

    public HttpResponse<String> getAnime (int animeId) throws IOException, InterruptedException;

    public HttpResponse<String> searchAnimes () throws IOException, InterruptedException;

    public HttpResponse<String> getTopAnimes (String category) throws IOException, InterruptedException;

    public HttpResponse<String> getAnimeRecommendations () throws IOException, InterruptedException;

    public HttpResponse<String> getAnimeReviews () throws IOException, InterruptedException;

    public HttpResponse<String> getSeasonalAnimes () throws IOException, InterruptedException;

    public HttpResponse<String> getAnimeGenres () throws IOException, InterruptedException;
}
