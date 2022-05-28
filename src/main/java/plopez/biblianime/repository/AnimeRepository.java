package plopez.biblianime.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plopez.biblianime.entity.Anime;
import plopez.biblianime.entity.Statut;

import java.util.List;

@Repository
public interface AnimeRepository extends CrudRepository<Anime, Long> {
    List<Anime> findByTitresNomContains(String titre);

    List<Anime> findByStatutIs(Statut statut);
}
