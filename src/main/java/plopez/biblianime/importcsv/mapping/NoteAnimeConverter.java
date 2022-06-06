package plopez.biblianime.importcsv.mapping;

import com.opencsv.bean.AbstractBeanField;
import plopez.biblianime.anime.entity.Note;

/**
 * Convertisseur de la note d'animé de nombre en énumération
 */
public class NoteAnimeConverter extends AbstractBeanField {
    @Override
    protected Note convert(String s) {
        return switch (s) {
            case "5" -> Note.CINQ;
            case "4" -> Note.QUATRE;
            case "3" -> Note.TROIS;
            case "2" -> Note.DEUX;
            case "1" -> Note.UN;
            default -> Note.ZERO;
        };

    }
}
