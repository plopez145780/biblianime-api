package plopez.biblianime.importcsv.entity;

import com.opencsv.bean.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import plopez.biblianime.anime.entity.Note;
import plopez.biblianime.anime.entity.Statut;
import plopez.biblianime.anime.entity.Type;
import plopez.biblianime.importcsv.mapping.NoteAnimeConverter;
import plopez.biblianime.importcsv.mapping.StatutAnimeConverter;
import plopez.biblianime.importcsv.mapping.TypeAnimeConverter;

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

    @CsvCustomBindByName(column = "Id_statut", converter = StatutAnimeConverter.class)
    Statut statut;

    @CsvCustomBindByName(column = "Id_note", converter = NoteAnimeConverter.class)
    Note note;

    @CsvCustomBindByName(column = "Id_type", converter = TypeAnimeConverter.class)
    Type type;

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
