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
import plopez.biblianime.apiexterne.myanimelist.enumeration.Season;
import plopez.biblianime.apiexterne.myanimelistofficiel.dto.AnimeDTO;

import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Tag(name = "animé", description = "API des animés")
@Validated
@RestController
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    @Autowired
    private AnimeMapper animeMapper;

    // CREATE

    @Deprecated
    @Operation(
            summary = "Ajouter un nouvel animé",
            description = "Ajouter un nouvel animé"
    )
    @PostMapping("/animes")
    public Anime add(@Valid @RequestBody Anime anime) {
        return animeService.add(anime);
    }

    @Operation(
            summary = "Ajouter un nouvel animé à partir de l'id de myAnimeList",
            description = "Ajouter un nouvel animé à partir de l'id de myAnimeList"
    )
    @PostMapping("/animes/{animeId}")
    public Boolean add(@Valid @NotNull @PathVariable("animeId") Integer animeId) {
        return animeService.add(animeId);
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
        return animeService.getAnimesBySeason(year, season).stream()
                .collect(groupingBy(AnimeDTO::getMediaType))
                .entrySet().stream()
                .map((animesBySeason) -> {
                    List<AnimeCardDTO> animeCardDTOS = animesBySeason.getValue().stream()
                            .map(animeMapper::toAnimeCardDTO)
                            .toList();
                    return new AnimesByTypeDTO(animesBySeason.getKey(), animeCardDTOS);
                })
                .collect(toList());
    }
}
