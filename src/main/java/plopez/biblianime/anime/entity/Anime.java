package plopez.biblianime.anime.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @OneToMany(mappedBy = "animeId")
    private List<AnimeTitle> titles;

    @NotNull
    @Column(nullable = false, columnDefinition = "varchar(10) default 'A_VOIR'")
    @Enumerated(EnumType.STRING)
    private AnimeStatut statut;

    @NotNull
    @Column(nullable = false, columnDefinition = "varchar(10) default 'ZERO'")
    @Enumerated(EnumType.STRING)
    private AnimeNote note;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AnimeType type;

    @NotNull
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer nbEpisodeVue;

    @NotNull
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer nbEpisodeTotal;

    private String wikipedia;

    private String nautiljon;

    private String commentaire;
}
