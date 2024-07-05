package plopez.biblianime.anime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnimeShortDTO {

    String title;
    String description;
    String pictureUrl;
    String myanimelistUrl;
    int myanimelistId;

}
