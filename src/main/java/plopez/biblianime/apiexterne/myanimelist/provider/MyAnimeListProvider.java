package plopez.biblianime.apiexterne.myanimelist.provider;

import java.net.http.HttpResponse;

public interface MyAnimeListProvider {

    HttpResponse<String> get(int id);

    HttpResponse<String> search(String query, Integer n, Integer score, Integer genre);

    HttpResponse<String> getTop(String category);

    HttpResponse<String> getRecommendations(Integer page);

    HttpResponse<String> getReviews(Integer page);

    HttpResponse<String> getGenres();
}
