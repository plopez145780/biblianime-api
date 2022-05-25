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
        return null;
    }

    @Override
    public void deleteAnimeById(Long animeId) {
        animeRepository.deleteById(animeId);
    }
}
