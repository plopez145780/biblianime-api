package plopez.biblianime.anime.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plopez.biblianime.anime.dto.AnimeCardDTO;
import plopez.biblianime.anime.dto.AnimesByTypeDTO;
import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.AnimeStatut;
import plopez.biblianime.anime.mapper.AnimeMapper;
import plopez.biblianime.anime.service.AnimeService;
import plopez.biblianime.apiexterne.myanimelist.dto.AnimeSeasonDTO;
import plopez.biblianime.apiexterne.myanimelist.enumeration.Season;
import plopez.biblianime.apiexterne.myanimelistofficiel.dto.AnimeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Deprecated
    @Operation(
            summary = "Ajouter un nouvel animé",
            description = "Ajouter un nouvel animé"
    )
    @PostMapping("/animes")
    public Anime add(@Valid @RequestBody Anime anime) {
        return animeService.save(anime);
    }

    // READ

    @Deprecated
    @Operation(
            summary = "Obtenir la liste des animés",
            description = "Obtenir la liste des animés"
    )
    @GetMapping("/animes")
    public List<Anime> findAll() {
        return animeService.findAll();
    }

    @Deprecated
    @Operation(
            summary = "Obtenir un animé",
            description = "Obtenir un animé"
    )
    @GetMapping("/animes/{id}")
    public Anime find(@PathVariable("id") Long animeId) {
        return animeService.find(animeId);
    }

    // UPDATE

    @Deprecated
    @Operation(
            summary = "Mettre à jour un animé",
            description = "Mettre à jour un animé"
    )
    @PutMapping("/animes/{id}")
    public Anime update(@RequestBody Anime anime, @PathVariable("id") Long animeId) {
        return animeService.update(anime, animeId);
    }

    // DELETE

    @Operation(
            summary = "Supprimer un animé",
            description = "Supprimer un animé"
    )
    @DeleteMapping("/animes/{id}")
    public String delete(@PathVariable("id") Long animeId) {
        animeService.delete(animeId);
        return "Deleted Successfully";
    }

    // AUTRES ACTIONS

    @Deprecated
    @Operation(
            summary = "Obtenir la liste des animés par titre",
            description = "Obtenir la liste des animés par titre"
    )
    @GetMapping("/animes/findByTitle")
    public List<Anime> findByTitle(@RequestParam("titre") String titleName) {
        return animeService.findByTitle(titleName);
    }

    @Deprecated
    @Operation(
            summary = "Obtenir la liste des animés par statut",
            description = "Obtenir la liste des animés par statut"
    )
    @GetMapping("/animes/findByStatut")
    public List<Anime> findByStatut(@RequestParam("statut") AnimeStatut statut) {
        return animeService.findByStatut(statut);
    }

    @Operation(
            summary = "Obtenir la liste des animés de la saison",
            description = "Obtenir la liste des animés par statut"
    )
    @GetMapping("/animes/season")
    public List<AnimesByTypeDTO> getAnimesBySeason(@RequestParam("year") int year,
                                                   @RequestParam("season") Season season) {
        Map<String, List<AnimeSeasonDTO>> animesBySeason = animeService.getAnimesBySeason(year, season);

        List<AnimesByTypeDTO> animesByTypeDTOS = new ArrayList<>();

        animesBySeason.forEach(
                (key, value) -> {
                    List<AnimeCardDTO> animeCardDTOS = value.stream()
                            .map(AnimeMapper::toAnimeCardDTO)
                            .toList();
                    AnimesByTypeDTO animesByTypeDTO = new AnimesByTypeDTO(key, animeCardDTOS);
                    animesByTypeDTOS.add(animesByTypeDTO);
                }
        );
        return animesByTypeDTOS;
    }
}
