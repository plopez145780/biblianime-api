package plopez.biblianime.myanimelist.anime.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class AnimeSeasonDTO {

    private String title;
    private String type;
    private String url;
    private List<Object> genres;
    @JsonProperty("image_url")
    private String imageUrl;
    private int score;
    private int members;
    private String synopsis;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private int episodes;
    private int duration;
    private Map<String, Object> properties = new LinkedHashMap<>();

    /* SETTER CUSTOM */

    @JsonSetter
    public void setDate(Map<String, Object> date) {
        this.date = new Date((int) (date.get("timestamp")) * 1000L);
    }

    @JsonAnySetter
    public void setProperties(String name, Object value) {
        this.properties.put(name, value);
    }
}

