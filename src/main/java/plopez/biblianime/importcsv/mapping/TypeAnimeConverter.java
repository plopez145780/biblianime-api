package plopez.biblianime.importcsv.mapping;

import com.opencsv.bean.AbstractBeanField;
import plopez.biblianime.anime.entity.Type;

/**
 * Convertisseur du type d'animé de string en énumération
 */
public class TypeAnimeConverter extends AbstractBeanField {
    @Override
    protected Type convert(String s) {
        return switch (s) {
            case "Série" -> Type.SERIE;
            case "Film" -> Type.FILM;
            case "OAV" -> Type.OAV;
            case "Spécial" -> Type.SPECIAL;
            case "ONA" -> Type.ONA;
            default -> null;
        };
    }
}
