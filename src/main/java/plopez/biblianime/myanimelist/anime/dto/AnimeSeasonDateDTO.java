package plopez.biblianime.myanimelist.anime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AnimeSeasonDateDTO {

    String date;
    Long timestamp;
}
