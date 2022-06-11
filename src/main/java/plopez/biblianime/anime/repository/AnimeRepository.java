package plopez.biblianime.anime.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.AnimeStatut;

import java.util.List;

@Repository
public interface AnimeRepository extends CrudRepository<Anime, Long> {
    List<Anime> findByTitlesNameContains(String titleName);

    List<Anime> findByStatutIs(AnimeStatut statut);
}
