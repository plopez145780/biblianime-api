package plopez.biblianime.service;

import plopez.biblianime.entity.Anime;

import java.util.List;

public interface AnimeService {

    Anime saveAnime(Anime anime);

    List<Anime> fetchAnimeList();

    Anime updateAnime(Anime anime, Long animeId);

    void deleteAnimeById(Long animeId);
}
