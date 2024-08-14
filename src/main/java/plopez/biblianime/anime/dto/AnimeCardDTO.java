package plopez.biblianime.anime.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AnimeCardDTO {

    @NotNull
    private Integer myanimelistId;

    @NotNull
    @NotBlank
    private String title;

    private List<String> genres;

    @Deprecated
    private String description;

    private String sysnopsis;

    private String imageUrl;

    private Integer totalEpisodes;

    private String url;
}
