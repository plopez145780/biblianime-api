package plopez.biblianime.anime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AnimeCardDTO {

    int myanimelistId;//extraire de l'URL https://myanimelist.net/anime/55701/Kimetsu_no_Yaiba__Hashira_Geiko-hen

    String title;

    List<String> genres;//pour le filtrage par genre dans le front

    @Deprecated
    String description;//=synopsis

    String sysnopsis;

    String imageUrl;

    int totalEpisodes;

    String url; //pour ouvrir la page de myAnimeList pour le detail
}
