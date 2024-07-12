package plopez.biblianime.myanimelist.provider;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface MyAnimeListProvider {

    HttpResponse<String> get(int id) throws IOException, InterruptedException;

    HttpResponse<String> search(String query, Integer n, Integer score, Integer genre) throws IOException, InterruptedException;

    HttpResponse<String> getTop(String category) throws IOException, InterruptedException;

    HttpResponse<String> getRecommendations(Integer page) throws IOException, InterruptedException;

    HttpResponse<String> getReviews(Integer page) throws IOException, InterruptedException;

    HttpResponse<String> getGenres() throws IOException, InterruptedException;
}
