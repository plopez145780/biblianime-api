package plopez.biblianime.myanimelist.provider;

import java.io.IOException;
import java.net.http.HttpResponse;

public class MyAnimeListMangaProviderImpl implements MyAnimeListProvider {
    @Override
    public HttpResponse<String> get(int id) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public HttpResponse<String> search(String query, Integer n, Integer score, Integer genre) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public HttpResponse<String> getTop(String category) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public HttpResponse<String> getRecommendations(Integer page) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public HttpResponse<String> getReviews(Integer page) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public HttpResponse<String> getGenres() throws IOException, InterruptedException {
        return null;
    }
}
