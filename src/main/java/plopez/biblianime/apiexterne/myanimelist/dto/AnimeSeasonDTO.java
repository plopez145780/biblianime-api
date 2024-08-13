package plopez.biblianime.apiexterne.myanimelist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Deprecated
@Getter
@Setter
@RequiredArgsConstructor
public class AnimeSeasonDTO {

    private String title;
    private String type;
    private String url;
    private List<GenreDTO> genres;
    @JsonProperty("image_url")
    private String imageUrl;
    private int score;
    private int members;
    private String synopsis;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private int episodes;
    private int duration;
    private PropertyDTO properties;

    /* SETTER CUSTOM */

    @JsonSetter
    public void setDate(DateDTO date) {
        this.date = new Date(date.getTimestamp() * 1000L);
    }
}

