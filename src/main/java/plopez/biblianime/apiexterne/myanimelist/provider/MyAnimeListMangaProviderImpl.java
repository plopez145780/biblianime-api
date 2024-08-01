package plopez.biblianime.apiexterne.myanimelist.provider;

import org.springframework.stereotype.Service;

@Deprecated
@Service
public class MyAnimeListMangaProviderImpl extends MyAnimeListProviderImpl implements MyAnimeListProvider {

    protected String TYPE = "manga/";

}
