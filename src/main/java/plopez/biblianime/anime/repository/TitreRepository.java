package plopez.biblianime.anime.repository;

import org.springframework.data.repository.CrudRepository;
import plopez.biblianime.anime.entity.Titre;

public interface TitreRepository extends CrudRepository<Titre, Long> {
}
