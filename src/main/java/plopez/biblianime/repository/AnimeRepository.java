package plopez.biblianime.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plopez.biblianime.entity.Anime;

@Repository
public interface AnimeRepository extends CrudRepository<Anime, Long> {
}
