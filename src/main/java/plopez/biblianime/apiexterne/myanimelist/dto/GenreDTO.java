package plopez.biblianime.apiexterne.myanimelist.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GenreDTO {

    private String name;
    private String url;

    public GenreDTO(String name) {
        this.name = name;
    }

    public GenreDTO(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
