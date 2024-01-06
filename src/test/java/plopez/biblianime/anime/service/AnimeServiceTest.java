package plopez.biblianime.anime.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import plopez.biblianime.anime.entity.Anime;
import plopez.biblianime.anime.entity.AnimeStatut;
import plopez.biblianime.anime.entity.AnimeTitle;
import plopez.biblianime.anime.repository.AnimeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test du service pour les animés")
class AnimeServiceTest {
    @Mock
    AnimeRepository animeRepository;
    @Mock
    TitleAnimeService titleAnimeService;
    @InjectMocks
    AnimeService animeService = new AnimeServiceImpl();

    @Test
    @DisplayName("Create Anime")
    void createAnime() {
        //Given
        AnimeTitle animeTitle = new AnimeTitle();
        animeTitle.setAnimeId(1L);
        animeTitle.setId(1L);
        animeTitle.setName("titre");
        animeTitle.setPrincipal(true);
        List<AnimeTitle> animeTitles = new ArrayList<>();
        animeTitles.add(animeTitle);

        Anime anime = new Anime();
        anime.setTitles(animeTitles);
        anime.setStatut(AnimeStatut.A_VOIR);

        Anime animeBdd = new Anime();
        animeBdd.setId(1L);

        when(animeRepository.save(anime)).thenReturn(animeBdd);

        //When
        Anime animeRetour = animeService.create(anime);

        //Then
        assertEquals(animeRetour.getId(), 1L);
    }
}