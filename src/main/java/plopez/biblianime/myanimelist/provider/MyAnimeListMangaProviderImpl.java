package plopez.biblianime.myanimelist.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;

@Service
public class MyAnimeListMangaProviderImpl implements MyAnimeListProvider {

    @Value("${api.myanimelist.base-url}")
    private String BASE_URL;

    @Value("${api.myanimelist.key}")
    private String X_RAPIDAPI_KEY;

    @Value("${api.myanimelist.host}")
    private String X_RAPIDAPI_HOST;

    @Override
    public HttpResponse<String> get(int id) throws IOException, InterruptedException {
        return request(BASE_URL + "manga/" + id, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> search(String query, Integer n, Integer score, Integer genre) throws IOException, InterruptedException {

        StringBuilder url = new StringBuilder(BASE_URL + "v2/manga/search");

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
        return request(BASE_URL + "manga/top/" + category, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> getRecommendations(Integer page) throws IOException, InterruptedException {
        if (page == null || page < 1) page = 1;
        return request(BASE_URL + "v2/manga/recommendations" + "?p=" + page, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> getReviews(Integer page) throws IOException, InterruptedException {
        if (page == null || page < 1) page = 1;
        return request(BASE_URL + "v2/manga/reviews" + "?p=" + page, X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }

    @Override
    public HttpResponse<String> getGenres() throws IOException, InterruptedException {
        return request(BASE_URL + "v2/manga/genres", X_RAPIDAPI_KEY, X_RAPIDAPI_HOST);
    }
}
