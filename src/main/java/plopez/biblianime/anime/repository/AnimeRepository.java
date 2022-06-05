package plopez.biblianime.anime.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.Statut;

import java.util.List;

@Repository
public interface AnimeRepository extends CrudRepository<Anime, Long> {
    List<Anime> findByTitresNomContains(String titre);

    List<Anime> findByStatutIs(Statut statut);
}
