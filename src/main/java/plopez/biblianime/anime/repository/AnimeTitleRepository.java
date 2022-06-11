package plopez.biblianime.anime.repository;

import org.springframework.data.repository.CrudRepository;
import plopez.biblianime.anime.entity.AnimeTitle;

import java.util.List;

public interface AnimeTitleRepository extends CrudRepository<AnimeTitle, Long> {

    List<AnimeTitle> findByAnimeId(Long animeId);

    List<AnimeTitle> findByNameContains(String titleName);
}
