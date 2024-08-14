package plopez.biblianime.anime.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plopez.biblianime.anime.dto.AnimeCardDTO;
import plopez.biblianime.anime.dto.AnimeDetailDTO;
import plopez.biblianime.anime.dto.AnimesByTypeDTO;
import plopez.biblianime.anime.entity.AnimeUserData;
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

    @Operation(
            summary = "Ajouter un nouvel animé",
            description = "Ajouter un nouvel animé"
    )
    @PostMapping("/animes")
    public Boolean add(@Valid @NotNull @RequestBody AnimeCardDTO animeCardDTO) {
        return animeService.add(animeCardDTO.getMyanimelistId());
    }

    // READ

    @Operation(
            summary = "Obtenir la liste des animés",
            description = "Obtenir la liste des animés"
    )
    @GetMapping("/animes")
    public List<AnimeCardDTO> findAll() {
        List<AnimeUserData> animesUserData = animeService.findAll();
        return animesUserData.stream().map(animeMapper::toAnimeCardDTO).collect(toList());
    }

    @Operation(
            summary = "Obtenir un animé",
            description = "Obtenir un animé"
    )
    @GetMapping("/animes/{id}")
    public AnimeDetailDTO find(@Valid @NotNull @PathVariable("id") Long animeId) {
        AnimeUserData animeUserData = animeService.find(animeId);
        return animeMapper.toAnimeDetailDTO(animeUserData);
    }

    // UPDATE

    @Operation(
            summary = "Mettre à jour un animé",
            description = "Mettre à jour un animé"
    )
    @PutMapping("/animes/{id}")
    public AnimeDetailDTO update(
            @Valid @NotNull @RequestBody AnimeDetailDTO animeDetailDTO,
            @Valid @NotNull @PathVariable("id") Long animeId) {
        return animeService.update(animeDetailDTO, animeId);
    }

    // DELETE

    @Operation(
            summary = "Supprimer un animé",
            description = "Supprimer un animé"
    )
    @DeleteMapping("/animes/{id}")
    public Boolean delete(@Valid @NotNull @PathVariable("id") Long animeId) {
        return animeService.delete(animeId);
    }

    // AUTRES ACTIONS

    @Operation(
            summary = "Obtenir la liste des animés de la saison",
            description = "Obtenir la liste des animés de la saison"
    )
    @GetMapping("/animes/season")
    public List<AnimesByTypeDTO> getAnimesBySeason(
            @Valid @NotNull
            @Min(value = 1900, message = "Sérieusement ?! Avant 1900 !!!")
            @Max(value = 2100, message = "Sérieusement ?! Après 2100 !!!")
            @RequestParam("year") Integer year,

            @Valid @NotNull @RequestParam("season") Season season
    ) {
        // TODO a refacto, trop complexe
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
