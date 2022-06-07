package plopez.biblianime.anime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plopez.biblianime.anime.entity.Titre;
import plopez.biblianime.anime.repository.TitreRepository;

import java.util.List;

@Service
public class TitreServiceImpl implements TitreService {

    @Autowired
    TitreRepository titreRepository;

    @Override
    public Titre saveTitre(Titre titre) {
        return titreRepository.save(titre);
    }

    @Override
    public List<Titre> findAll() {
        return (List<Titre>) titreRepository.findAll();
    }

    @Override
    public Titre updateTitre(Titre titre, Long titreId) {
        return null;
    }

    @Override
    public void deleteTitreById(Long titreId) {
        titreRepository.deleteById(titreId);
    }

    @Override
    public List<Titre> findByNom(String nom) {
        return null;
    }
}
