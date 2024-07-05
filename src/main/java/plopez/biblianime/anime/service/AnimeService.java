package plopez.biblianime.anime.service;

import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.AnimeInformation;
import plopez.biblianime.anime.entity.AnimeStatut;
import plopez.biblianime.anime.entity.Season;

import java.util.List;

public interface AnimeService {

    Anime create(Anime anime);

    Anime save(Anime anime);

    List<Anime> findAll();

    Anime update(Anime anime, Long animeId);

    void delete(Long animeId);

    List<Anime> findByTitle(String titre);

    List<Anime> findByStatut(AnimeStatut statut);

    Anime find(Long animeId);

    List<AnimeInformation> getAnimesBySeason(int year, Season season);
}
