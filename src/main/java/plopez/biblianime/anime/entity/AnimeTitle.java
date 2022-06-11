package plopez.biblianime.anime.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Titre d'animé
 */
@Getter
@Setter
@Entity
public class AnimeTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Boolean principal;

    @Column(name = "id_anime")
    private Long animeId;
}
