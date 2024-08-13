package plopez.biblianime.anime.mapper;

import org.springframework.stereotype.Component;
import plopez.biblianime.anime.entity.AnimeData;
import plopez.biblianime.apiexterne.myanimelistofficiel.dto.AnimeDTO;

@Component
public class AnimeDataMapper {

    public AnimeData toAnimeData(AnimeDTO animeDTO) {

        AnimeData animeData = new AnimeData();

        animeData.setMyanimelistId(animeDTO.getId());
        animeData.setTitle(animeDTO.getTitle());

        return animeData;
    }
}
