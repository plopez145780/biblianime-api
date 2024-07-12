package plopez.biblianime.anime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AnimeCardDTO {

    String title;
    String url;//pour ouvrir la page de myAnimeList pour le detail
    List<String> genres;//pour le filtrage par genre dans le front
    String description;//=synopsis
    String imageUrl;
    int totalEpisodes;
    int myanimelistId;//extraire de l'URL https://myanimelist.net/anime/55701/Kimetsu_no_Yaiba__Hashira_Geiko-hen

}
