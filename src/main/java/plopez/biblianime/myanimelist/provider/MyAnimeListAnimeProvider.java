package plopez.biblianime.myanimelist.provider;

import plopez.biblianime.myanimelist.Season;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface MyAnimeListAnimeProvider extends MyAnimeListProvider {
    HttpResponse<String> getSeasonal(int year, Season season) throws IOException, InterruptedException;
}
