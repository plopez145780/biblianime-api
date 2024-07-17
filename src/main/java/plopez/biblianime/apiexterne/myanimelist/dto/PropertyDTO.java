package plopez.biblianime.apiexterne.myanimelist.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PropertyDTO {
    private String source;
    private GenreDTO studio;
    private GenreDTO studios;
    private GenreDTO theme;
    private GenreDTO themes;
    private GenreDTO demographic;
    private GenreDTO demographics;
}
