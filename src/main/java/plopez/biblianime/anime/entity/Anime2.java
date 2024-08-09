package plopez.biblianime.anime.entity;

import lombok.Data;

import java.util.List;

@Data
public class Anime2 {

    private String title;
    private List<String> alternativeTitle;
    private String englishTitle;
    private String japTitle;
    private String url; //pour ouvrir la page de myAnimeList pour le detail
    private List<String> genres;//pour le filtrage par genre dans le front
    private String description;//=synopsis
    private String imageUrl;
    private int totalEpisodes;
    private int myanimelistId; //extraire de l'URL https://myanimelist.net/anime/55701/Kimetsu_no_Yaiba__Hashira_Geiko-hen


}
