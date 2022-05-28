package plopez.biblianime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plopez.biblianime.entity.Anime;
import plopez.biblianime.repository.AnimeRepository;

import java.util.List;

@Service
public class AnimeServiceImpl implements AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    @Override
    public Anime saveAnime(Anime anime) {
        return animeRepository.save(anime);
    }

    @Override
    public List<Anime> fetchAnimeList() {
        return (List<Anime>) animeRepository.findAll();
    }

    @Override
    public Anime updateAnime(Anime anime, Long animeId) {

        Anime animeEntity = animeRepository.findById(animeId).orElse(new Anime());

        if (animeEntity.getStatut() != Statut.EN_COUR && anime.getStatut() == Statut.EN_COUR) {
            animeEntity.setDateDebut(LocalDate.now());
        }

        if (animeEntity.getStatut() != Statut.FINIT && anime.getStatut() == Statut.FINIT) {
            animeEntity.setDateFin(LocalDate.now());
        }
        animeEntity.setTitres(anime.getTitres());
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
    public void deleteAnimeById(Long animeId) {
        animeRepository.deleteById(animeId);
    }
}
