package plopez.biblianime.anime.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
public class AnimeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer myanimelistId;

    private String title;
    private String titleEn;
    private String titleJa;
    //synonyms

    private String mainPictureMedium;
    private String mainPictureLarge;

    private LocalDate startDate;
    private LocalDate endDate;

    private String synopsis; //texte formater, warning

    private Float mean;

    private Integer rank;

    private Integer popularity;

    private Integer numListUsers;

    private Integer numScoringUsers;

    private String nsfw; //(Enum ?)

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String mediaType; //Enum ?

    private String status; //Enum ?

    //private List<GenreDTO> genres;

    //private MyListStatusDTO myListStatus; //FONCTIONNEL ?

    private Integer numEpisodes;

    private String seasonYear;
    private String season;

    private String BroadcastDayOfTheWeek; //Enum ?
    private LocalTime BroadcastStartTime;

    private String source;//Enum ?

    private Integer averageEpisodeDuration;

    private String rating; //Enum ?

    //private List<PictureDTO> pictures;

    private String background;

    //private List<RelatedDTO> relatedAnime;
    //private List<RelatedDTO> relatedManga;
    //private List<RecommendationDTO> recommendations;
    //private List<StudioDTO> studios;
    //private StatisticsDTO statistics;
}
