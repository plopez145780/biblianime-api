package plopez.biblianime.service;

import plopez.biblianime.entity.Anime;
import plopez.biblianime.entity.Statut;

import java.util.List;

public interface AnimeService {

    Anime saveAnime(Anime anime);

    List<Anime> findAll();

    Anime updateAnime(Anime anime, Long animeId);

    void deleteAnimeById(Long animeId);

    List<Anime> findByTitre(String titre);

    List<Anime> findByStatut(Statut statut);
}
