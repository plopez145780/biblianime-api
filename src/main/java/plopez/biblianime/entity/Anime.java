package plopez.biblianime.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private NoteAnime note;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeAnime type;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer nbEpisodeVue;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer nbEpisodeTotal;

    private String wikipedia;

    private String nautiljon;

    private String commentaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public List<Titre> getTitres() {
        return titres;
    }

    public void setTitres(List<Titre> titres) {
        this.titres = titres;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public NoteAnime getNote() {
        return note;
    }

    public void setNote(NoteAnime note) {
        this.note = note;
    }

    public TypeAnime getType() {
        return type;
    }

    public void setType(TypeAnime type) {
        this.type = type;
    }

    public Integer getNbEpisodeVue() {
        return nbEpisodeVue;
    }

    public void setNbEpisodeVue(Integer nbEpisodeVue) {
        this.nbEpisodeVue = nbEpisodeVue;
    }

    public Integer getNbEpisodeTotal() {
        return nbEpisodeTotal;
    }

    public void setNbEpisodeTotal(Integer nbEpisodeTotal) {
        this.nbEpisodeTotal = nbEpisodeTotal;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getNautiljon() {
        return nautiljon;
    }

    public void setNautiljon(String nautiljon) {
        this.nautiljon = nautiljon;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
