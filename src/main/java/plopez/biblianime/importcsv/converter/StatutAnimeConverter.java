package plopez.biblianime.importcsv.converter;

import com.opencsv.bean.AbstractBeanField;
import plopez.biblianime.anime.entity.AnimeStatut;

/**
 * Convertisseur du statut d'animé de string en énumération
 */
public class StatutAnimeConverter extends AbstractBeanField {
    @Override
    protected AnimeStatut convert(String s) {
        return switch (s) {
            case "Finit" -> AnimeStatut.FINIT;
            case "En attente" -> AnimeStatut.EN_ATTENTE;
            case "En cour" -> AnimeStatut.EN_COUR;
            default -> AnimeStatut.A_VOIR; // Pour les A_VOIR et les cas sans valeur correcte
        };
    }
}
