package plopez.biblianime.anime.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Data
@Entity
public class AnimeUserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private AnimeData animeData;

    //private Long animeDataId;

    @CreatedDate
    //@Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @LastModifiedDate
    //@Column(nullable = false)
    private LocalDate updatedAt;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private AnimeStatut statut = AnimeStatut.A_VOIR;

    @Enumerated(EnumType.STRING)
    private AnimeNote note = AnimeNote.ZERO;

    private Integer nbEpisodeVue = 0;

    private String commentaire;

    private String urlStream;
}
