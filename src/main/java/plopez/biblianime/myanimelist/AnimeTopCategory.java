package plopez.biblianime.myanimelist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AnimeTopCategory {
    ALL("all"),
    AIRING("airing"),
    UPCOMING("upcoming"),
    TV("tv"),
    MOVIE("movie"),
    OVA("ova"),
    ONA("ona"),
    SPECIAL("special"),
    BYPOPULARITY("bypopularity"),
    FAVORITE("favorite");

    private final String value;
}
