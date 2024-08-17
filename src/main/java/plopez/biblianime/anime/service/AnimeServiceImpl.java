package plopez.biblianime.anime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import plopez.biblianime.anime.dto.AnimeCardDTO;
import plopez.biblianime.anime.dto.AnimeDetailDTO;
import plopez.biblianime.anime.entity.*;
import plopez.biblianime.anime.mapper.AnimeDataMapper;
import plopez.biblianime.anime.repository.AnimeRepository;
import plopez.biblianime.anime.repository.AnimeUserDataRepository;
import plopez.biblianime.apiexterne.myanimelist.enumeration.Season;
import plopez.biblianime.apiexterne.myanimelist.service.MyAnimeListAnimeService;
import plopez.biblianime.apiexterne.myanimelistofficiel.dto.AnimeDTO;
import plopez.biblianime.apiexterne.myanimelistofficiel.service.MyAnimeListOfficielService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeServiceImpl implements AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private AnimeUserDataRepository animeUserDataRepository;

    @Autowired
    private TitleAnimeService titleAnimeService;

    @Autowired
    private MyAnimeListAnimeService myAnimeListAnimeService;

    @Autowired
    private MyAnimeListOfficielService myAnimeListOfficielService;

    @Autowired
    private AnimeDataMapper animeDataMapper;


    @Override
    public Anime create(Anime anime) {
        Anime animeBdd = animeRepository.save(anime);
        Long id = animeBdd.getId();

        List<AnimeTitle> animeTitleAsauvegarder = anime.getTitles();
        animeTitleAsauvegarder.forEach(titre -> titre.setAnimeId(id));

        titleAnimeService.save(animeTitleAsauvegarder);
        return animeBdd;
    }

    @Override
    public Anime add(Anime anime) {
        return animeRepository.save(anime);
    }

    @Override
    public Boolean add(Integer animeId) {
        Boolean success = Boolean.TRUE;

        AnimeDTO animeDetails = null;

        // appel api, sinon save un obj dt ode l'interface graphique (actuellement c'est un obj par defaut)
        try {
            animeDetails = myAnimeListOfficielService.getAnimeDetails(animeId, null);
        } catch (Exception e) {
            List<String> genres = new ArrayList<>();
            genres.add("GENRE");
            AnimeCardDTO animeCardDTO = new AnimeCardDTO(123456, "TITRE", genres, "DESCRIPTION", "SYNOPSIS", "IMAGE", 12, "URL");
            animeDetails = new AnimeDTO();
            //mapper animeCardDTO to AnimeDTO
        }


        try {
            AnimeUserData animeUserData = new AnimeUserData();
            AnimeData animeData = animeDataMapper.toAnimeData(animeDetails);
            animeUserData.setAnimeData(animeData);

            animeUserDataRepository.save(animeUserData);
        } catch (Exception e) {
            success = Boolean.FALSE;
        }
        return success;
    }

    @Override
    public List<AnimeUserData> findAll() {
        return (List<AnimeUserData>) animeUserDataRepository.findAll();
    }

    @Override
    public AnimeUserData find(Long animeId) {
        return animeUserDataRepository.findById(animeId).orElse(null);
    }

    @Override
    public AnimeDetailDTO update(AnimeDetailDTO animeDetailDTO, Long animeId) {
        //TODO update la BDD avec les info de animeDetailDTO
        /*Anime animeEntity = animeRepository.findById(animeId).orElse(new Anime());

        if (animeEntity.getStatut() != AnimeStatut.EN_COUR && anime.getStatut() == AnimeStatut.EN_COUR) {
            animeEntity.setDateDebut(LocalDate.now());
        }

        if (animeEntity.getStatut() != AnimeStatut.FINIT && anime.getStatut() == AnimeStatut.FINIT) {
            animeEntity.setDateFin(LocalDate.now());
        }
        animeEntity.setTitles(anime.getTitles());
        animeEntity.setStatut(anime.getStatut());
        animeEntity.setNote(anime.getNote());
        animeEntity.setType(anime.getType());
        animeEntity.setNbEpisodeVue(anime.getNbEpisodeVue());
        animeEntity.setNbEpisodeTotal(anime.getNbEpisodeTotal());
        animeEntity.setNautiljon(anime.getNautiljon());
        animeEntity.setWikipedia(anime.getWikipedia());
        animeEntity.setCommentaire(anime.getCommentaire());

        return animeRepository.save(animeEntity);*/
        return null;
    }

    @Override
    public Boolean delete(Long animeId) {
        Boolean success = Boolean.TRUE;
        try {
            animeUserDataRepository.deleteById(animeId);
        } catch (Exception e) {
            success = Boolean.FALSE;
        }
        return success;
    }

    @Override
    public List<Anime> findByTitle(String titre) {
        return animeRepository.findByTitlesNameContains(titre);
    }

    @Override
    public List<Anime> findByStatut(AnimeStatut statut) {
        return animeRepository.findByStatutIs(statut);
    }


    @Cacheable("animesBySeasonCache")
    @Override
    public List<AnimeDTO> getAnimesBySeason(Integer year, Season season) {
        return myAnimeListOfficielService.getSeasonalAnime(year, season.toString(), null, null, null, null);
    }

}
