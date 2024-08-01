package plopez.biblianime.apiexterne.myanimelistofficiel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatisticsStatusDTO {

    private Integer watching;

    private Integer completed;

    @JsonProperty("on_hold")
    private Integer onHold;

    private Integer dropped;

    @JsonProperty("plan_to_watch")
    private Integer planToWatch;
}
