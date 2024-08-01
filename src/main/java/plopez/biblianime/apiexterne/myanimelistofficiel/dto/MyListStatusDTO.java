package plopez.biblianime.apiexterne.myanimelistofficiel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class MyListStatusDTO {

    private String status; //Enum ?

    private Integer score;

    @JsonProperty("num_episodes_watched")
    private Integer numEpisodesWatched;

    @JsonProperty("is_rewatching")
    private Boolean isRewatching;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSzz")
    private LocalDateTime updatedAt;
}
