package plopez.biblianime.importcsv.converter;

import plopez.biblianime.anime.entity.AnimeTitle;

public class AnimeTitleConverter {

    /**
     * Crée et renvoie un objet AnimeTitle avec le titre donné et l'ID de l'anime associé.
     *
     * @param title   le titre de l'AnimeTitle
     * @param animeId l'ID de l'anime associé à l'AnimeTitle
     * @return l'objet AnimeTitle créé
     */
    public static AnimeTitle convert(String title, Long animeId) {
        AnimeTitle animeTitle = new AnimeTitle();
        animeTitle.setAnimeId(animeId);
        animeTitle.setName(title);
        return animeTitle;
    }
}
