package plopez.biblianime.anime.service;

import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.AnimeStatut;
import plopez.biblianime.apiexterne.myanimelist.enumeration.Season;
import plopez.biblianime.apiexterne.myanimelistofficiel.dto.AnimeDTO;

import java.util.List;

public interface AnimeService {

    Anime create(Anime anime);

    Anime add(Anime anime);

    Boolean add(Integer animeId);

    List<Anime> findAll();

    Anime update(Anime anime, Long animeId);

    void delete(Long animeId);

    List<Anime> findByTitle(String titre);

    List<Anime> findByStatut(AnimeStatut statut);

    Anime find(Long animeId);

    List<AnimeDTO> getAnimesBySeason(Integer year, Season season);
}
