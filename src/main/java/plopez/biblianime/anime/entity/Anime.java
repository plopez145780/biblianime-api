package plopez.biblianime.anime.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "id_anime")
    private List<Titre> titres;

    @Column(nullable = false, columnDefinition = "varchar(10) default 'A_VOIR'")
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @Column(nullable = false, columnDefinition = "varchar(10) default 'ZERO'")
    @Enumerated(EnumType.STRING)
    private Note note;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer nbEpisodeVue;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer nbEpisodeTotal;

    private String wikipedia;

    private String nautiljon;

    private String commentaire;
}
