package plopez.biblianime.myanimelist.anime.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import plopez.biblianime.anime.entity.Season;
import plopez.biblianime.myanimelist.anime.dto.AnimeSeasonDTO;
import plopez.biblianime.myanimelist.anime.provider.MyAnimeListAnimeProvider;

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
    private static String jdd() {
        return "{\n" +
                "  \"TV (New)\": [\n" +
                "    {\n" +
                "      \"title\": \"Tate no Yuusha no Nariagari Season 3\",\n" +
                "      \"type\": \"TV (New)\",\n" +
                "      \"url\": \"https://myanimelist.net/anime/40357/Tate_no_Yuusha_no_Nariagari_Season_3\",\n" +
                "      \"genres\": [\n" +
                "        {\n" +
                "          \"name\": \"Action\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/1/Action\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Adventure\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/2/Adventure\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Drama\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/8/Drama\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Fantasy\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/10/Fantasy\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"image_url\": \"https://cdn.myanimelist.net/images/anime/1188/136926.jpg\",\n" +
                "      \"score\": 7.51,\n" +
                "      \"members\": 368000,\n" +
                "      \"synopsis\": \"After defeating the Spirit Tortoise, Naofumi has no time for rest. An attack from the next Guardian Beast is imminent, but the three other Cardinal Heroes have gone missing. So, Naofumi and his party set out to search for the legendary trio.\\r\\n\\r\\n(Source: Crunchyroll)\",\n" +
                "      \"date\": {\n" +
                "        \"date\": \"2023-10-06\",\n" +
                "        \"timestamp\": 1696550400\n" +
                "      },\n" +
                "      \"episodes\": 12,\n" +
                "      \"duration\": 23,\n" +
                "      \"properties\": {\n" +
                "        \"studio\": {\n" +
                "          \"name\": \"Kinema Citrus\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/producer/290/Kinema_Citrus\"\n" +
                "        },\n" +
                "        \"source\": \"Light novel\",\n" +
                "        \"theme\": {\n" +
                "          \"name\": \"Isekai\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/62/Isekai\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Spy x Family Season 2\",\n" +
                "      \"type\": \"TV (New)\",\n" +
                "      \"url\": \"https://myanimelist.net/anime/53887/Spy_x_Family_Season_2\",\n" +
                "      \"genres\": [\n" +
                "        {\n" +
                "          \"name\": \"Action\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/1/Action\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Comedy\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/4/Comedy\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"image_url\": \"https://cdn.myanimelist.net/images/anime/1506/138982.jpg\",\n" +
                "      \"score\": 8.18,\n" +
                "      \"members\": 338000,\n" +
                "      \"synopsis\": \"With her ability to read minds, Anya Forger is the only one who knows the true identities of her unconventional family. Her pretend father Loid operates as an elite spy code-named Twilight; her mother Yor kills on demand as the assassin Thorn Princess; and their dog, Bond, possesses the gift of precognition. Although they hide the truth from each other, this pretense of a perfectly ordinary family provides Anya with the genuine love and warmth that she longed for as an orphan.\\r\\n\\r\\nOperation Strix—Loid's special mission to avoid potential war by gathering vital information and getting close to the powerful political figure, Donovan Desmond—is only possible if Anya plays her part right. She can either excel academically and become an Imperial Scholar at her prestigious school or make friends with Donovan's son, Damian. Neither is exactly easy, but with her adventurous attitude, Anya throws herself wholeheartedly into her mission as a Forger—all for the sake of international peace.\\r\\n\\r\\n[Written by MAL Rewrite]\",\n" +
                "      \"date\": {\n" +
                "        \"date\": \"2023-10-07\",\n" +
                "        \"timestamp\": 1696636800\n" +
                "      },\n" +
                "      \"episodes\": 12,\n" +
                "      \"duration\": 23,\n" +
                "      \"properties\": {\n" +
                "        \"studio\": {\n" +
                "          \"name\": \"CloverWorks\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/producer/1835/CloverWorks\"\n" +
                "        },\n" +
                "        \"source\": \"Manga\",\n" +
                "        \"theme\": {\n" +
                "          \"name\": \"Childcare\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/53/Childcare\"\n" +
                "        },\n" +
                "        \"demographic\": {\n" +
                "          \"name\": \"Shounen\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/27/Shounen\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Sousou no Frieren\",\n" +
                "      \"type\": \"TV (New)\",\n" +
                "      \"url\": \"https://myanimelist.net/anime/52991/Sousou_no_Frieren\",\n" +
                "      \"genres\": [\n" +
                "        {\n" +
                "          \"name\": \"Adventure\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/2/Adventure\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Drama\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/8/Drama\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Fantasy\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/10/Fantasy\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"image_url\": \"https://cdn.myanimelist.net/images/anime/1015/138006.jpg\",\n" +
                "      \"score\": 9.1,\n" +
                "      \"members\": 310000,\n" +
                "      \"synopsis\": \"During their decade-long quest to defeat the Demon King, the members of the hero's party—Himmel himself, the priest Heiter, the dwarf warrior Eisen, and the elven mage Frieren—forge bonds through adventures and battles, creating unforgettable precious memories for most of them.\\r\\n\\r\\nHowever, the time that Frieren spends with her comrades is equivalent to merely a fraction of her life, which has lasted over a thousand years. When the party disbands after their victory, Frieren casually returns to her \\\"usual\\\" routine of collecting spells across the continent. Due to her different sense of time, she seemingly holds no strong feelings toward the experiences she went through.\\r\\n\\r\\nAs the years pass, Frieren gradually realizes how her days in the hero's party truly impacted her. Witnessing the deaths of two of her former companions, Frieren begins to regret having taken their presence for granted; she vows to better understand humans and create real personal connections. Although the story of that once memorable journey has long ended, a new tale is about to begin.\\r\\n\\r\\n[Written by MAL Rewrite]\",\n" +
                "      \"date\": {\n" +
                "        \"date\": \"2023-09-29\",\n" +
                "        \"timestamp\": 1695945600\n" +
                "      },\n" +
                "      \"episodes\": 28,\n" +
                "      \"duration\": 24,\n" +
                "      \"properties\": {\n" +
                "        \"studio\": {\n" +
                "          \"name\": \"Madhouse\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/producer/11/Madhouse\"\n" +
                "        },\n" +
                "        \"source\": \"Manga\",\n" +
                "        \"demographic\": {\n" +
                "          \"name\": \"Shounen\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/27/Shounen\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Goblin Slayer II\",\n" +
                "      \"type\": \"TV (New)\",\n" +
                "      \"url\": \"https://myanimelist.net/anime/47160/Goblin_Slayer_II\",\n" +
                "      \"genres\": [\n" +
                "        {\n" +
                "          \"name\": \"Action\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/1/Action\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Adventure\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/2/Adventure\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Fantasy\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/10/Fantasy\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"image_url\": \"https://cdn.myanimelist.net/images/anime/1100/138338.jpg\",\n" +
                "      \"score\": 7.51,\n" +
                "      \"members\": 275000,\n" +
                "      \"synopsis\": \"Second season of Goblin Slayer.\",\n" +
                "      \"date\": {\n" +
                "        \"date\": \"2023-10-06\",\n" +
                "        \"timestamp\": 1696550400\n" +
                "      },\n" +
                "      \"episodes\": 12,\n" +
                "      \"duration\": 23,\n" +
                "      \"properties\": {\n" +
                "        \"studio\": {\n" +
                "          \"name\": \"LIDENFILMS\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/producer/839/LIDENFILMS\"\n" +
                "        },\n" +
                "        \"source\": \"Light novel\",\n" +
                "        \"theme\": {\n" +
                "          \"name\": \"Gore\",\n" +
                "          \"url\": \"https://myanimelist.net/anime/genre/58/Gore\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
/*
    @Test
    public void testGetSeasonalAnimes_IOException() {
        // Arrange
        int year = 2023;
        Season season = Season.WINTER;
        MyAnimeListAnimeProvider mockProvider = Mockito.mock(MyAnimeListAnimeProvider.class);
        MyAnimeListAnimeServiceImpl service = new MyAnimeListAnimeServiceImpl(mockProvider);
        Mockito.doThrow(IOException.class).when(mockProvider).getSeasonalAnimes(year, season);

        // Act & Assert
        Assert.assertThrows(IOException.class, () -> service.getSeasonalAnimes(year, season));
    }

    @Test
    public void testGetSeasonalAnimes_InterruptedException() {
        // Arrange
        int year = 2023;
        Season season = Season.WINTER;
        MyAnimeListAnimeProvider mockProvider = Mockito.mock(MyAnimeListAnimeProvider.class);
        MyAnimeListAnimeServiceImpl service = new MyAnimeListAnimeServiceImpl(mockProvider);
        Mockito.doThrow(InterruptedException.class).when(mockProvider).getSeasonalAnimes(year, season);

        // Act & Assert
        Assert.assertThrows(InterruptedException.class, () -> service.getSeasonalAnimes(year, season));
    }

    @Test
    public void testGetSeasonalAnimes_OtherException() {
        // Arrange
        int year = 2023;
        Season season = Season.WINTER;
        MyAnimeListAnimeProvider mockProvider = Mockito.mock(MyAnimeListAnimeProvider.class);
        MyAnimeListAnimeServiceImpl service = new MyAnimeListAnimeServiceImpl(mockProvider);
        Mockito.doThrow(RuntimeException.class).when(mockProvider).getSeasonalAnimes(year, season);

        // Act & Assert
        Assert.assertThrows(RuntimeException.class, () -> service.getSeasonalAnimes(year, season));
    }*/

    @Test
    public void testGetSeasonalAnimes_Success() throws IOException, InterruptedException {
        // Given
        int year = 2024;
        Season season = Season.SPRING;

        String json = jdd();
        HttpResponse<String> httpResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(httpResponse.body()).thenReturn(json);

        Mockito.when(myAnimeListAnimeProvider.getSeasonalAnimes(year, season)).thenReturn(httpResponse);

        // When
        Map<String, List<AnimeSeasonDTO>> result = service.getSeasonalAnimes(year, season);
        // Then
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Tate no Yuusha no Nariagari Season 3", result.get("TV (New)").getFirst().getTitle());

    }
}