package plopez.biblianime.apiexterne.myanimelistofficiel.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnimePagingDTO {

    private List<NodeAnimeDTO> data;

    private PagingDTO paging;

    private SeasonDTO season;
}
