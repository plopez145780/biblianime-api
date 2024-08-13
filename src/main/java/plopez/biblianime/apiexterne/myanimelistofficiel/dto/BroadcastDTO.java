package plopez.biblianime.apiexterne.myanimelistofficiel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalTime;

@Data
public class BroadcastDTO {

    @JsonProperty("day_of_the_week")
    private String dayOfTheWeek; //Enum ?

    @JsonProperty("start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;
}
