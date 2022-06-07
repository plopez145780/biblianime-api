package plopez.biblianime.anime.service;

import plopez.biblianime.anime.entity.Titre;

import java.util.List;

public interface TitreService {
    Titre saveTitre(Titre titre);

    List<Titre> findAll();

    Titre updateTitre(Titre titre, Long titreId);

    void deleteTitreById(Long titreId);

    List<Titre> findByNom(String nom);
}
