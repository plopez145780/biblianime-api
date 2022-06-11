package plopez.biblianime.anime.service;

import plopez.biblianime.anime.entity.AnimeTitle;

import java.util.List;

public interface TitleAnimeService {
    AnimeTitle save(AnimeTitle animeTitle);

    List<AnimeTitle> save(List<AnimeTitle> animeTitleList);

    List<AnimeTitle> findAll();

    AnimeTitle update(AnimeTitle animeTitle, Long titreId);

    void deleteById(Long titreId);

    List<AnimeTitle> findByName(String nom);

    List<AnimeTitle> findByAnimeId(Long animeId);
}
