package plopez.biblianime.anime.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plopez.biblianime.anime.entity.AnimeTitle;
import plopez.biblianime.anime.service.TitleAnimeService;

import java.util.List;

@Tag(name = "titre", description = "API des titres")
@RestController
public class AnimeTitleController {
    @Autowired
    TitleAnimeService titleAnimeService;

    @Operation(summary = "Ajouter un nouveau titre à un animé", description = "Ajouter un nouveau titre à un animé")
    @PostMapping("/animes/{animeId}/titles")
    public AnimeTitle saveTitle(@RequestBody AnimeTitle animeTitle) {
        return titleAnimeService.save(animeTitle);
    }

    @Operation(summary = "Ajouter plusieur nouveaux titres à un animé", description = "Ajouter plusieur nouveaux titres à un animé")
    @PostMapping("/animes/{animeId}/titles/list")
    public List<AnimeTitle> saveTitleList(@RequestBody List<AnimeTitle> animeTitleList) {
        return titleAnimeService.save(animeTitleList);
    }

    @Operation(summary = "Obtenir la liste des titres", description = "Obtenir la liste des titres")
    @GetMapping("anime/titres")
    public List<AnimeTitle> fetchAllTitreList() {
        return titleAnimeService.findAll();
    }

    @Operation(summary = "Obtenir la liste des titres d'un animé", description = "Obtenir la liste des titres d'un animé")
    @GetMapping("anime/{animeId}/titres")
    public List<AnimeTitle> fetchAnimeTitreList(@PathVariable("animeId") Long animeId) {
        return titleAnimeService.findByAnimeId(animeId);
    }

    @Operation(summary = "Obtenir la liste des titres par nom", description = "Obtenir la liste des titres par nom")
    @GetMapping("anime/titres/findByNom")
    public List<AnimeTitle> findByNom(@RequestParam("nom") String nom) {
        return titleAnimeService.findByName(nom);
    }

    // UPDATE
    @Operation(summary = "Mettre à jour un titre", description = "Mettre à jour un titre")
    @PutMapping("anime/{animeId}/titres/{titreId}")
    public AnimeTitle updateTitre(@RequestBody AnimeTitle animeTitle, @PathVariable("titreId") Long titreId) {
        return titleAnimeService.update(animeTitle, titreId);
    }

    // DELETE
    @Operation(summary = "Supprimer un titre", description = "Supprimer un titre")
    @DeleteMapping("/{id}")
    public String deleteTitreById(@PathVariable("id") Long titreId) {
        titleAnimeService.deleteById(titreId);
        return "Deleted Successfully";
    }

}
