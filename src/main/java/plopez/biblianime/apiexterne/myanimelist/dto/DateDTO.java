package plopez.biblianime.apiexterne.myanimelist.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Deprecated
@Getter
@Setter
@RequiredArgsConstructor
public class DateDTO {
    private String date;
    private long timestamp;
}
