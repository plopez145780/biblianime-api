package plopez.biblianime.anime.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plopez.biblianime.anime.dto.AnimeShortDTO;
import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.AnimeInformation;
import plopez.biblianime.anime.entity.AnimeStatut;
import plopez.biblianime.anime.entity.Season;
import plopez.biblianime.anime.mapper.AnimeMapper;
import plopez.biblianime.anime.service.AnimeService;

import java.util.List;

/**
 * Controller pour les animés
 */
@Tag(name = "animé", description = "API des animés")
@Validated
@RestController
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    // CREATE
    @Operation(summary = "Ajouter un nouvel animé", description = "Ajouter un nouvel animé")
    @PostMapping("/animes")
    public Anime add(@Valid @RequestBody Anime anime) {
        return animeService.save(anime);
    }

    // READ
    @Operation(summary = "Obtenir la liste des animés", description = "Obtenir la liste des animés")
    @GetMapping("/animes")
    public List<Anime> findAll() {
        return animeService.findAll();
    }

    @Operation(summary = "Obtenir un animé", description = "Obtenir un animé")
    @GetMapping("/animes/{id}")
    public Anime find(@PathVariable("id") Long animeId) {
        return animeService.find(animeId);
    }

    // UPDATE
    @Operation(summary = "Mettre à jour un animé", description = "Mettre à jour un animé")
    @PutMapping("/animes/{id}")
    public Anime update(@RequestBody Anime anime, @PathVariable("id") Long animeId) {
        return animeService.update(anime, animeId);
    }

    // DELETE
    @Operation(summary = "Supprimer un animé", description = "Supprimer un animé")
    @DeleteMapping("/animes/{id}")
    public String delete(@PathVariable("id") Long animeId) {
        animeService.delete(animeId);
        return "Deleted Successfully";
    }

    // AUTRES ACTIONS
    @Operation(summary = "Obtenir la liste des animés par titre", description = "Obtenir la liste des animés par titre")
    @GetMapping("/animes/findByTitle")
    public List<Anime> findByTitle(@RequestParam("titre") String titleName) {
        return animeService.findByTitle(titleName);
    }

    @Operation(summary = "Obtenir la liste des animés par statut", description = "Obtenir la liste des animés par statut")
    @GetMapping("/animes/findByStatut")
    public List<Anime> findByStatut(@RequestParam("statut") AnimeStatut statut) {
        return animeService.findByStatut(statut);
    }


    @Operation(summary = "Obtenir la liste des animés de la saison", description = "Obtenir la liste des animés par statut")
    @GetMapping("/animes/saisons")
    public List<AnimeShortDTO> getAnimesBySeason(@RequestParam("year") int year,
                                                 @RequestParam("season") Season season) {
        List<AnimeInformation> animesBySeason = animeService.getAnimesBySeason(year, season);
        return animesBySeason.stream()
                .map(AnimeMapper::toAnimeShortDTO)
                .toList();
    }

    @Operation(summary = "Save l'animé", description = "Obtenir la liste des animés par statut")
    @PostMapping("/animes/saisons")
    public List<Anime> saveAnimesBySeason(@RequestParam("year") int year,
                                          @RequestParam("season") Season season) {
        return null;
    }


}
