package plopez.biblianime.importcsv.converter;

import com.opencsv.bean.AbstractBeanField;
import plopez.biblianime.anime.entity.Statut;

/**
 * Convertisseur du statut d'animé de string en énumération
 */
public class StatutAnimeConverter extends AbstractBeanField {
    @Override
    protected Statut convert(String s) {
        return switch (s) {
            case "Finit" -> Statut.FINIT;
            case "En attente" -> Statut.EN_ATTENTE;
            case "En cour" -> Statut.EN_COUR;
            default -> Statut.A_VOIR; // Pour les A_VOIR et les cas sans valeur correcte
        };
    }
}
