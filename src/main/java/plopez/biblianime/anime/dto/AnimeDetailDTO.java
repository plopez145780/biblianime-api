package plopez.biblianime.anime.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AnimeDetailDTO {
    @NotNull
    private Integer myanimelistId;

    @NotNull
    @NotBlank
    private String title;

    private List<String> genres;

    private String sysnopsis;

    private String imageUrl;

    private Integer totalEpisodes;

    @NotNull
    @NotBlank
    private String url;
}
