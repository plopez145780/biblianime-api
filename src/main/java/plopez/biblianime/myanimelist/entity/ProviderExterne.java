package plopez.biblianime.myanimelist.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProviderExterne {
    MYANIMELIST("myanimelist", 100L),
    WEBTOON("webtoon", 500L);

    private final String titre;
    private final Long count;
}
