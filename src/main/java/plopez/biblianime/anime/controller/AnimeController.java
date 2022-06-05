package plopez.biblianime.anime.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.Statut;
import plopez.biblianime.anime.service.AnimeService;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlleur pour les animés
 */
@Tag(name = "animé", description = "API des animés")
@RequestMapping("/animes")
@RestController
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    @Operation(summary = "Ajouter un nouvel animé", description = "Ajouter un nouvel animé")
    @PostMapping("/")
    public Anime saveAnime(@Valid @RequestBody Anime anime) {
        return animeService.saveAnime(anime);
    }

    @Operation(summary = "Obtenir la liste des animés", description = "Obtenir la liste des animés")
    @GetMapping("/")
    public List<Anime> fetchAnimeList() {
        return animeService.findAll();
    }

    @Operation(summary = "Obtenir la liste des animés par titre", description = "Obtenir la liste des animés par titre")
    @GetMapping("/findByTitre")
    public List<Anime> findByTitre(@RequestParam("titre") String titre) {
        return animeService.findByTitre(titre);
    }

    @Operation(summary = "Obtenir la liste des animés par statut", description = "Obtenir la liste des animés par statut")
    @GetMapping("/findByStatut")
    public List<Anime> findByStatut(@RequestParam("statut") Statut statut) {
        return animeService.findByStatut(statut);
    }

    @Operation(summary = "Mettre à jour un animé", description = "Mettre à jour un animé")
    @PutMapping("/{id}")
    public Anime updateAnime(@RequestBody Anime anime, @PathVariable("id") Long animeId) {
        return animeService.updateAnime(anime, animeId);
    }

    @Operation(summary = "Supprimer un animé", description = "Supprimer un animé")
    @DeleteMapping("/{id}")
    public String deleteAnimeById(@PathVariable("id") Long animeId) {
        animeService.deleteAnimeById(animeId);
        return "Deleted Successfully";
    }
}
