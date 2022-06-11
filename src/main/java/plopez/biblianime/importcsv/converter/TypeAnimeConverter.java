package plopez.biblianime.importcsv.converter;

import com.opencsv.bean.AbstractBeanField;
import plopez.biblianime.anime.entity.AnimeType;

/**
 * Convertisseur du type d'animé de string en énumération
 */
public class TypeAnimeConverter extends AbstractBeanField {
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
