package plopez.biblianime.apiexterne.myanimelist.provider;

import plopez.biblianime.apiexterne.myanimelist.Season;

import java.net.http.HttpResponse;

public interface MyAnimeListAnimeProvider extends MyAnimeListProvider {
    HttpResponse<String> getSeasonal(int year, Season season);
}
