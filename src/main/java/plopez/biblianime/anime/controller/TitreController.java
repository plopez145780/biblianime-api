package plopez.biblianime.anime.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plopez.biblianime.anime.entity.Titre;
import plopez.biblianime.anime.service.TitreService;

import java.util.List;

@Tag(name = "titre", description = "API des titres")
@RequestMapping("/titres")
@RestController
public class TitreController {
    @Autowired
    TitreService titreService;

    @Operation(summary = "Ajouter un nouveau titre", description = "Ajouter un nouveau titre")
    @PostMapping("/")
    public Titre saveTitre(@RequestBody Titre titre) {
        return titreService.saveTitre(titre);
    }

    @Operation(summary = "Obtenir la liste des titres", description = "Obtenir la liste des titres")
    @GetMapping("/")
    public List<Titre> fetchTitreList() {
        return titreService.findAll();
    }

    @Operation(summary = "Obtenir la liste des titres par nom", description = "Obtenir la liste des titres par nom")
    @GetMapping("/findByNom")
    public List<Titre> findByNom(@RequestParam("nom") String nom) {
        return titreService.findByNom(nom);
    }

    @Operation(summary = "Mettre à jour un titre", description = "Mettre à jour un titre")
    @PutMapping("/{id}")
    public Titre updateTitre(@RequestBody Titre titre, @PathVariable("id") Long titreId) {
        return titreService.updateTitre(titre, titreId);
    }

    @Operation(summary = "Supprimer un titre", description = "Supprimer un titre")
    @DeleteMapping("/{id}")
    public String deleteTitreById(@PathVariable("id") Long titreId) {
        titreService.deleteTitreById(titreId);
        return "Deleted Successfully";
    }

}
