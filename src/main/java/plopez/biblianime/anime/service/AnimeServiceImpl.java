package plopez.biblianime.anime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import plopez.biblianime.anime.controller.AnimeController;
import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.Statut;
import plopez.biblianime.anime.repository.AnimeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AnimeServiceImpl implements AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    @Override
    public Anime saveAnime(Anime anime) {
        return animeRepository.save(anime);
    }

    @Override
    public CollectionModel<Anime> findAll() {
        Iterable<Anime> animes = animeRepository.findAll();
        Link link = linkTo(methodOn(AnimeController.class).fetchAnimeList()).withSelfRel();
        return CollectionModel.of(animes, link);
    }

    @Override
    public EntityModel<Anime> findOne(Long animeId) {
        Optional<Anime> anime = animeRepository.findById(animeId);
        if (anime.isPresent()) {
            Link link = linkTo(methodOn(AnimeController.class).fetchAnime(animeId)).withSelfRel();
            return EntityModel.of(anime.get(), link);
        }
        return null;
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

    @Override
    public List<Anime> findByTitre(String titre) {
        return animeRepository.findByTitresNomContains(titre);
    }

    @Override
    public List<Anime> findByStatut(Statut statut) {
        return animeRepository.findByStatutIs(statut);
    }

}
