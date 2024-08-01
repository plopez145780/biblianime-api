package plopez.biblianime.apiexterne.myanimelistofficiel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AnimeDTO {

    private Integer id;

    private String title;

    @JsonProperty("main_picture")
    private PictureDTO mainPicture;

    @JsonProperty("alternative_titles")
    private TitleDTO alternativeTitles;

    @JsonProperty("start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonProperty("end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String synopsis; //texte formater, warning

    private Float mean;

    private Integer rank;

    private Integer popularity;

    @JsonProperty("num_list_users")
    private Integer numListUsers;

    @JsonProperty("num_scoring_users")
    private Integer numScoringUsers;

    String nsfw; //(Enum ?)

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime updatedAt;

    @JsonProperty("media_type")
    private String mediaType; //Enum ?

    private String status; //Enum ?

    private List<GenreDTO> genres;

    @JsonProperty("my_list_status")
    private MyListStatusDTO myListStatus; //FONCTIONNEL ?

    @JsonProperty("num_episodes")
    private Integer numEpisodes;

    @JsonProperty("start_season")
    private SeasonDTO startSeason;

    private BroadcastDTO broadcast;

    private String source;//Enum ?

    @JsonProperty("average_episode_duration")
    private Integer averageEpisodeDuration;

    private String rating; //Enum ?

    private List<PictureDTO> pictures;

    private String background;

    @JsonProperty("related_anime")
    private List<RelatedDTO> relatedAnime;

    @JsonProperty("related_manga")
    private List<RelatedDTO> relatedManga;

    private List<RecommendationDTO> recommendations;

    private List<StudioDTO> studios;

    private StatisticsDTO statistics;
}
