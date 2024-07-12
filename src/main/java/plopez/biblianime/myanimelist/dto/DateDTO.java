package plopez.biblianime.myanimelist.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DateDTO {
    private String date;
    private long timestamp;
}
