package plopez.biblianime.myanimelist.anime.service;

class MyAnimeListAnimeServiceImplTest {

    /*@Test
    public void testGetSeasonalAnimes_Success() throws IOException, InterruptedException {
        // Given
        int year = 2023;
        Season season = Season.WINTER;

        MyAnimeListAnimeServiceImpl service = new MyAnimeListAnimeServiceImpl();



        //String expectedAnimeName = "Test Anime";
        //MyAnimeListAnimeProvider mockProvider = Mockito.mock(MyAnimeListAnimeProvider.class);
        //MyAnimeListAnimeServiceImpl service = new MyAnimeListAnimeServiceImpl(mockProvider);
        //AnimeInformation anime = new AnimeInformation();
        //anime.setTitle(expectedAnimeName);
        //AnimeInformation[] animes = new AnimeInformation[]{anime};
        //String json = new ObjectMapper().writeValueAsString(animes);
        //HttpResponse<String> response = Mockito.mock(HttpResponse.class);
        //Mockito.when(response.body()).thenReturn(json);
        //Mockito.when(mockProvider.getSeasonalAnimes(year, season)).thenReturn(response);

        // When
        List<AnimeInformation> result = service.getSeasonalAnimes(year, season);
        // Then
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("tintin", result.get(0).getTitle());

    }*/
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
}