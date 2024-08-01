package plopez.biblianime.apiexterne.myanimelistofficiel.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RecommendationDTO {

    private NodeDTO node;

    @JsonProperty("num_recommendations")
    private Integer numRecommendations;
}
