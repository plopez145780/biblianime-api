package plopez.biblianime.importcsv.converter;

import com.opencsv.bean.AbstractBeanField;
import plopez.biblianime.anime.entity.AnimeNote;

/**
 * Convertisseur de la note d'animé de nombre en énumération
 */
public class NoteAnimeConverter extends AbstractBeanField {
    @Override
    protected AnimeNote convert(String s) {
        return switch (s) {
            case "5" -> AnimeNote.CINQ;
            case "4" -> AnimeNote.QUATRE;
            case "3" -> AnimeNote.TROIS;
            case "2" -> AnimeNote.DEUX;
            case "1" -> AnimeNote.UN;
            default -> AnimeNote.ZERO; // Pour les 0 et les cas sans valeur
        };

    }
}
