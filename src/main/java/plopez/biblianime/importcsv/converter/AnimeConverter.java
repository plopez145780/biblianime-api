package plopez.biblianime.importcsv.converter;

import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.AnimeTitle;
import plopez.biblianime.importcsv.bean.AnimeCsv;

import java.util.List;

public class AnimeConverter {
    public static Anime convert(AnimeCsv animeCsv, List<AnimeTitle> animeTitles) {
        Anime anime = new Anime();
        anime.setId(animeCsv.getId());
        anime.setDateDebut(animeCsv.getAjouteLe());
        anime.setDateFin(animeCsv.getModifieLe());
        anime.setTitles(animeTitles);
        anime.setStatut(animeCsv.getStatut());
        anime.setNote(animeCsv.getNote());
        anime.setType(animeCsv.getType());
        anime.setNbEpisodeVue(animeCsv.getNbEpisodesVue());
        anime.setNbEpisodeTotal(animeCsv.getNbEpisodesTotal());
        anime.setNautiljon(animeCsv.getNautiljon());
        anime.setWikipedia(animeCsv.getWikipedia());
        anime.setCommentaire(animeCsv.getCommentaire());
        return anime;
    }
}
