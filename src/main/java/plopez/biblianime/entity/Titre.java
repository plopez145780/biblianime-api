package plopez.biblianime.entity;

import javax.persistence.*;

@Entity
public class Titre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private Boolean principal;

    private Integer id_anime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public Integer getId_anime() {
        return id_anime;
    }

    public void setId_anime(Integer id_anime) {
        this.id_anime = id_anime;
    }
}
