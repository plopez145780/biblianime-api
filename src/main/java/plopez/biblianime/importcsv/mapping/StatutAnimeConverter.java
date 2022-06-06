package plopez.biblianime.importcsv.mapping;

import com.opencsv.bean.AbstractBeanField;
import plopez.biblianime.anime.entity.Statut;

/**
 * Convertisseur du statut d'animé de string en énumération
 */
public class StatutAnimeConverter extends AbstractBeanField {
    @Override
    protected Statut convert(String s) {
        return switch (s) {
            case "A voir" -> Statut.A_VOIR;
            case "En cour" -> Statut.EN_COUR;
            case "En attente" -> Statut.EN_ATTENTE;
            case "Finit" -> Statut.FINIT;
            default -> null;
        };
    }
}
