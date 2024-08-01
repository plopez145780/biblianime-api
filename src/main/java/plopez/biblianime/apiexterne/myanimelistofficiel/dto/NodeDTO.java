package plopez.biblianime.apiexterne.myanimelistofficiel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NodeDTO {

    private Integer id;

    private String title;

    @JsonProperty("main_picture")
    private PictureDTO mainPicture;
}
