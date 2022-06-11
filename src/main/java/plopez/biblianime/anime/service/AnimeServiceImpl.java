package plopez.biblianime.anime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.AnimeStatut;
import plopez.biblianime.anime.entity.AnimeTitle;
import plopez.biblianime.anime.repository.AnimeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnimeServiceImpl implements AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private TitleAnimeService titleAnimeService;

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
    public Anime save(Anime anime) {
        return animeRepository.save(anime);
    }

    @Override
    public List<Anime> findAll() {
        return (List<Anime>) animeRepository.findAll();
    }

    @Override
    public Anime find(Long animeId) {
        return animeRepository.findById(animeId).orElse(null);
    }

    @Override
    public Anime update(Anime anime, Long animeId) {

        Anime animeEntity = animeRepository.findById(animeId).orElse(new Anime());

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

        return animeRepository.save(animeEntity);
    }

    @Override
    public void delete(Long animeId) {
        animeRepository.deleteById(animeId);
    }

    @Override
    public List<Anime> findByTitle(String titre) {
        return animeRepository.findByTitlesNameContains(titre);
    }

    @Override
    public List<Anime> findByStatut(AnimeStatut statut) {
        return animeRepository.findByStatutIs(statut);
    }

}
