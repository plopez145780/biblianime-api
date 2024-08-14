package plopez.biblianime.anime.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import plopez.biblianime.anime.dto.AnimeCardDTO;
import plopez.biblianime.anime.service.AnimeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimeControllerTest {

    @Mock
    private AnimeService animeService;

    @InjectMocks
    private AnimeController animeController;

    //TODO
    // Ajout d'anime
    // Depuis la page des animes de la saison
    // Cliquer sur add pour ajouter l'animes a la collection
    // Appelle de l'api add
    // le controlleur gere la validation des entrees, l'appel au service et le mappage en DTO
    // CAS de test :
    // 1 OK
    // 2 KO
    // 3 NULL

    @Test
    public void testAddAnime() {
        // Arrange
        AnimeCardDTO animeCardDTO = new AnimeCardDTO(123, "aaa", null, null, null, null, null, null);
        when(animeService.add(123)).thenReturn(true);

        // Act
        Boolean response = animeController.add(animeCardDTO);

        // Assert
        assertEquals(true, response);
    }

    @Test
    public void testAddAnimeKO() {
        // Arrange
        AnimeCardDTO animeCardDTO = new AnimeCardDTO(123, "aaa", null, null, null, null, null, null);
        when(animeService.add(123)).thenReturn(false);

        // Act
        Boolean response = animeController.add(animeCardDTO);

        // Assert
        assertEquals(false, response);
    }

    @Test
    public void testAddAnimeNull() {
        // Act
        Exception exception = assertThrows(NullPointerException.class, () -> animeController.add(null));

        // Assert
        assertEquals("Cannot invoke \"plopez.biblianime.anime.dto.AnimeCardDTO.getMyanimelistId()\" because \"animeCardDTO\" is null", exception.getMessage());
    }

}