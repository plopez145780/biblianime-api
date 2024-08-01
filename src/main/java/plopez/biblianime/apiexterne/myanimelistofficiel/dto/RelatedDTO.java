package plopez.biblianime.apiexterne.myanimelistofficiel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RelatedDTO {

    private NodeDTO node;

    @JsonProperty("relation_type")
    private String relationType; //enum ?

    @JsonProperty("relation_type_formatted")
    private String relationTypeFormatted;

}
