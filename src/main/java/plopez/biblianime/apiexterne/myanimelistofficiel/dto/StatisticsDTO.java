package plopez.biblianime.apiexterne.myanimelistofficiel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatisticsDTO {

    private StatisticsStatusDTO status;

    @JsonProperty("num_list_users")
    private Integer numListUsers;

}
