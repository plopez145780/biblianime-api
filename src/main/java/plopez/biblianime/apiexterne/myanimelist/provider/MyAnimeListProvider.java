package plopez.biblianime.apiexterne.myanimelist.provider;

import java.net.http.HttpResponse;

public interface MyAnimeListProvider {

    HttpResponse<String> get(int id);

    HttpResponse<String> search(String query, Integer n, Integer score, String genre);

    //HttpResponse<String> getTop(Integer page, String category);

    //HttpResponse<String> getRecommendations(Integer page);

    //HttpResponse<String> getRecommendationsByRessource(int id);

    //HttpResponse<String> getReviews(Integer page, Boolean spoilers, Boolean preliminary, String includeTags, String excludeTags);

    //HttpResponse<String> getReviewsByRessources(Integer page, Boolean spoilers, Boolean preliminary, String includeTags, String excludeTags, int id);

    HttpResponse<String> getGenres();
}
