package plopez.biblianime.anime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AnimesByTypeDTO {
    private String type;
    private List<AnimeCardDTO> animeCardDTOS;
}
