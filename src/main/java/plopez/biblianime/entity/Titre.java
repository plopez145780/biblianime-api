package plopez.biblianime.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Titre d'animé
 */
@Getter
@Setter
@Entity
public class Titre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private Boolean principal;

    private Integer id_anime;
}
