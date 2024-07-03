package plopez.biblianime.importcsv.converter;

import com.opencsv.bean.AbstractBeanField;
import plopez.biblianime.anime.entity.AnimeType;

/**
 * Convertisseur du type d'animé de string en énumération
 */
public class AnimeTypeConverter extends AbstractBeanField {

    /**
     * Convertit une représentation sous forme de chaîne d'un type d'anime en sa valeur d'énumération correspondante.
     *
     * @param s la représentation sous forme de chaîne du type d'anime
     * @return la valeur d'énumération AnimeType correspondante, ou null si la chaîne n'est pas reconnue
     */
    @Override
    protected AnimeType convert(String s) {
        return switch (s) {
            case "Série" -> AnimeType.SERIE;
            case "Film" -> AnimeType.FILM;
            case "OAV" -> AnimeType.OAV;
            case "Spécial" -> AnimeType.SPECIAL;
            case "ONA" -> AnimeType.ONA;
            default -> null;
        };
    }
}
