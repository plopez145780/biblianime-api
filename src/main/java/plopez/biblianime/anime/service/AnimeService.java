package plopez.biblianime.anime.service;

import plopez.biblianime.anime.dto.AnimeDetailDTO;
import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.AnimeStatut;
import plopez.biblianime.anime.entity.AnimeUserData;
import plopez.biblianime.apiexterne.myanimelist.enumeration.Season;
import plopez.biblianime.apiexterne.myanimelistofficiel.dto.AnimeDTO;

import java.util.List;

public interface AnimeService {

    @Deprecated
    Anime create(Anime anime);

    @Deprecated
    Anime add(Anime anime);

    Boolean add(Integer animeId);

    List<AnimeUserData> findAll();

    AnimeUserData find(Long animeId);

    AnimeDetailDTO update(AnimeDetailDTO animeDetailDTO, Long animeId);

    Boolean delete(Long animeId);

    @Deprecated
    List<Anime> findByTitle(String titre);

    @Deprecated
    List<Anime> findByStatut(AnimeStatut statut);

    List<AnimeDTO> getAnimesBySeason(Integer year, Season season);
}
