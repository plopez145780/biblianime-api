package plopez.biblianime.anime.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plopez.biblianime.anime.entity.AnimeUserData;

@Repository
public interface AnimeUserDataRepository extends CrudRepository<AnimeUserData, Long> {
}
