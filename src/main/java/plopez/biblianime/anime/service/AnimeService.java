package plopez.biblianime.anime.service;

import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.AnimeStatut;
import plopez.biblianime.apiexterne.myanimelist.Season;
import plopez.biblianime.apiexterne.myanimelist.dto.AnimeSeasonDTO;

import java.util.List;
import java.util.Map;

public interface AnimeService {

    Anime create(Anime anime);

    Anime save(Anime anime);

    List<Anime> findAll();

    Anime update(Anime anime, Long animeId);

    void delete(Long animeId);

    List<Anime> findByTitle(String titre);

    List<Anime> findByStatut(AnimeStatut statut);

    Anime find(Long animeId);

    Map<String, List<AnimeSeasonDTO>> getAnimesBySeason(int year, Season season);
}
