package plopez.biblianime.anime.service;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.Statut;

import java.util.List;

public interface AnimeService {
    Anime saveAnime(Anime anime);

    CollectionModel<Anime> findAll();

    Anime updateAnime(Anime anime, Long animeId);

    void deleteAnimeById(Long animeId);

    List<Anime> findByTitre(String titre);

    List<Anime> findByStatut(Statut statut);

    EntityModel<Anime> findOne(Long animeId);
}
