package plopez.biblianime.apiexterne.myanimelistofficiel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AnimePagingDTO {

    private List<AnimeDTO> data;

    private PagingDTO paging;
}
