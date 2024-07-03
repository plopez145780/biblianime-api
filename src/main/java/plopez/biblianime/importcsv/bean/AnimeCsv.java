package plopez.biblianime.importcsv.bean;

import com.opencsv.bean.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import plopez.biblianime.anime.entity.AnimeNote;
import plopez.biblianime.anime.entity.AnimeStatut;
import plopez.biblianime.anime.entity.AnimeType;
import plopez.biblianime.importcsv.converter.AnimeNoteConverter;
import plopez.biblianime.importcsv.converter.AnimeStatutConverter;
import plopez.biblianime.importcsv.converter.AnimeTypeConverter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class AnimeCsv {

    @CsvBindByName(column = "Id")
    Long id;

    @CsvBindByName(column = "Ajoute_le")
    @CsvDate(value = "dd/MM/yyyy")
    LocalDate ajouteLe;

    @CsvBindByName(column = "Modifie_le")
    @CsvDate(value = "dd/MM/yyyy")
    LocalDate modifieLe;

    @CsvBindAndSplitByName(column = "Titre", splitOn = "/", elementType = String.class)
    List<String> titres;

    @CsvCustomBindByName(column = "Id_statut", converter = AnimeStatutConverter.class)
    AnimeStatut statut;

    @CsvCustomBindByName(column = "Id_note", converter = AnimeNoteConverter.class)
    AnimeNote note;

    @CsvCustomBindByName(column = "Id_type", converter = AnimeTypeConverter.class)
    AnimeType type;

    @CsvBindByName(column = "Nb_episodes_vue")
    @CsvNumber("#")
    Integer nbEpisodesVue;

    @CsvBindByName(column = "Nb_episodes_total")
    @CsvNumber("#")
    Integer nbEpisodesTotal;

    @CsvBindByName(column = "Wikipedia")
    String wikipedia;

    @CsvBindByName(column = "Nautiljon")
    String nautiljon;

    @CsvBindByName(column = "Commentaire")
    String commentaire;
}
