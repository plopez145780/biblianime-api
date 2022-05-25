package plopez.biblianime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plopez.biblianime.entity.Anime;
import plopez.biblianime.service.AnimeService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    @PostMapping("/animes")
    public Anime saveAnime(@Valid @RequestBody Anime anime) {
        return animeService.saveAnime(anime);
    }

    @GetMapping("/animes")
    public List<Anime> fetchAnimeList() {
        return animeService.fetchAnimeList();
    }

    @PutMapping("/animes/{id}")
    public Anime updateAnime(@RequestBody Anime anime, @PathVariable("id") Long animeId) {
        return animeService.updateAnime(anime, animeId);
    }

    @DeleteMapping("/animes/{id}")
    public String deleteAnimeById(@PathVariable("id") Long animeId) {
        animeService.deleteAnimeById(animeId);
        return "Deleted Successfully";
    }
}
