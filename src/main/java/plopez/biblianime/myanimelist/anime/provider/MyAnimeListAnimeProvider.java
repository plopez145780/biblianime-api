package plopez.biblianime.myanimelist.anime.provider;

import plopez.biblianime.myanimelist.anime.AnimeTopCategory;
import plopez.biblianime.myanimelist.anime.Season;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface MyAnimeListAnimeProvider {

    HttpResponse<String> getAnime(int animeId) throws IOException, InterruptedException;

    HttpResponse<String> searchAnimes(String query, Integer n, Integer score, Integer genre) throws IOException, InterruptedException;

    HttpResponse<String> getTopAnimes(AnimeTopCategory category) throws IOException, InterruptedException;

    HttpResponse<String> getAnimeRecommendations(Integer page) throws IOException, InterruptedException;

    HttpResponse<String> getAnimeReviews(Integer page) throws IOException, InterruptedException;

    HttpResponse<String> getSeasonalAnimes(int year, Season season) throws IOException, InterruptedException;

    HttpResponse<String> getAnimeGenres() throws IOException, InterruptedException;
}
