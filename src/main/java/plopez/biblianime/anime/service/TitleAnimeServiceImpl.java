package plopez.biblianime.anime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plopez.biblianime.anime.entity.AnimeTitle;
import plopez.biblianime.anime.repository.AnimeTitleRepository;

import java.util.List;

@Service
public class TitleAnimeServiceImpl implements TitleAnimeService {

    @Autowired
    AnimeTitleRepository animeTitleRepository;

    @Override
    public AnimeTitle save(AnimeTitle animeTitle) {
        return animeTitleRepository.save(animeTitle);
    }

    @Override
    public List<AnimeTitle> save(List<AnimeTitle> animeTitleList) {
        return (List<AnimeTitle>) animeTitleRepository.saveAll(animeTitleList);
    }

    @Override
    public List<AnimeTitle> findAll() {
        return (List<AnimeTitle>) animeTitleRepository.findAll();
    }

    @Override
    public AnimeTitle update(AnimeTitle animeTitle, Long titleId) {
        animeTitle.setId(titleId);
        return animeTitleRepository.save(animeTitle);
    }

    @Override
    public void deleteById(Long titleId) {
        animeTitleRepository.deleteById(titleId);
    }

    @Override
    public List<AnimeTitle> findByName(String titleName) {
        return animeTitleRepository.findByNameContains(titleName);
    }

    @Override
    public List<AnimeTitle> findByAnimeId(Long animeId) {
        return animeTitleRepository.findByAnimeId(animeId);
    }
}
