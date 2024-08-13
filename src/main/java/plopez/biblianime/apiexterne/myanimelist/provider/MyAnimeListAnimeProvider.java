package plopez.biblianime.apiexterne.myanimelist.provider;

import plopez.biblianime.apiexterne.myanimelist.enumeration.Season;

import java.net.http.HttpResponse;

@Deprecated
public interface MyAnimeListAnimeProvider extends MyAnimeListProvider {
    HttpResponse<String> getSeasonal(int year, Season season);
}
