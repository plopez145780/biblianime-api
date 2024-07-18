package plopez.biblianime.apiexterne.myanimelist.provider;

import org.springframework.stereotype.Service;
import plopez.biblianime.apiexterne.entity.ProviderExterne;

@Service
public class MyAnimeListMangaProviderImpl extends MyAnimeListProviderImpl implements MyAnimeListProvider {

    protected String TYPE = "manga/";

    private ProviderExterne providerExterne = ProviderExterne.MYANIMELIST;
}
