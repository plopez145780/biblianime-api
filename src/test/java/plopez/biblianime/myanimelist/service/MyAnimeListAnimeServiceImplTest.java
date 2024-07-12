package plopez.biblianime.myanimelist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import plopez.biblianime.myanimelist.Season;
import plopez.biblianime.myanimelist.dto.AnimeSeasonDTO;
import plopez.biblianime.myanimelist.provider.MyAnimeListAnimeProvider;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class MyAnimeListAnimeServiceImplTest {

    // MOCK
    @Mock
    private MyAnimeListAnimeProvider myAnimeListAnimeProvider;

    @InjectMocks
    private MyAnimeListAnimeServiceImpl service;

    // JDD

    private static String jdd0() {
        return """
                {
                    "TV (New)":[],
                    "TV (Continuing)":[],
                    "ONA":[],
                    "OVA":[],
                    "Movie":[],
                    "Special":[]
                }
                """;
    }

    private static String jdd() {
        return """
                {"TV (New)": [{
                    "title": "Spy x Family Season 2",
                    "type": "TV (New)",
                    "url": "https://myanimelist.net/anime/53887/Spy_x_Family_Season_2",
                    "genres": [{
                        "name": "Action",
                        "url": "https://myanimelist.net/anime/genre/1/Action"
                    },
                    {
                        "name": "Comedy",
                        "url": "https://myanimelist.net/anime/genre/4/Comedy"
                    }],
                    "image_url": "https://cdn.myanimelist.net/images/anime/1506/138982.jpg",
                    "score": 8.18,
                    "members": 338000,
                    "synopsis": "With her ability to read minds, Anya Forger is the only one who knows the true identities of her unconventional family. Her pretend father Loid operates as an elite spy code-named Twilight; her mother Yor kills on demand as the assassin Thorn Princess; and their dog, Bond, possesses the gift of precognition. Although they hide the truth from each other, this pretense of a perfectly ordinary family provides Anya with the genuine love and warmth that she longed for as an orphan.Operation Strix—Loid's special mission to avoid potential war by gathering vital information and getting close to the powerful political figure, Donovan Desmond—is only possible if Anya plays her part right. She can either excel academically and become an Imperial Scholar at her prestigious school or make friends with Donovan's son, Damian. Neither is exactly easy, but with her adventurous attitude, Anya throws herself wholeheartedly into her mission as a Forger—all for the sake of international peace.[Written by MAL Rewrite]",
                    "date": {
                        "date": "2023-10-07",
                        "timestamp": 1696636800
                    },
                    "episodes": 12,
                    "duration": 23,
                    "properties": {
                        "studio": {
                            "name": "CloverWorks",
                            "url": "https://myanimelist.net/anime/producer/1835/CloverWorks"
                        },
                        "source": "Manga",
                        "theme": {
                            "name": "Childcare",
                            "url": "https://myanimelist.net/anime/genre/53/Childcare"
                        },
                        "demographic": {
                            "name": "Shounen",
                            "url": "https://myanimelist.net/anime/genre/27/Shounen"
                        }
                    }
                }]}""";
    }

    @Test
    public void testGetSeasonalAnimes_Success() throws IOException, InterruptedException {
        // Given
        int year = 2024;
        Season season = Season.SPRING;

        HttpResponse<String> httpResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(httpResponse.body()).thenReturn(jdd());
        Mockito.when(myAnimeListAnimeProvider.getSeasonal(year, season)).thenReturn(httpResponse);

        // When
        Map<String, List<AnimeSeasonDTO>> resultat = service.getSeasonalAnimes(year, season);

        // Then
        Assertions.assertEquals(1, resultat.size());
        Assertions.assertEquals(1, resultat.get("TV (New)").size());
        Assertions.assertEquals("Spy x Family Season 2", resultat.get("TV (New)").getFirst().getTitle());
    }
}