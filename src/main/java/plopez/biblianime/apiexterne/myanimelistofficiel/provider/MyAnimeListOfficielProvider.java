package plopez.biblianime.apiexterne.myanimelistofficiel.provider;

import jakarta.validation.constraints.NotNull;

import java.net.http.HttpResponse;

public interface MyAnimeListOfficielProvider {

    HttpResponse<String> getAnimeList(String query, Integer limit, Integer offset, String fields);

    HttpResponse<String> getAnimeDetails(@NotNull Integer animeId, String fields);

    HttpResponse<String> getAnimeRanking(@NotNull String rankingType, Integer limit, Integer offset, String fields);

    HttpResponse<String> getSeasonalAnime(@NotNull Integer year, @NotNull String season, String sort, Integer limit, Integer offset, String fields);
}
