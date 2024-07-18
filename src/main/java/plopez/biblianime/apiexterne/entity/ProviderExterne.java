package plopez.biblianime.apiexterne.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProviderExterne {
    MYANIMELIST("myanimelist", 100),
    WEBTOON("webtoon", 500);

    private final String titre;
    private final int requestLimite; // Nombre de requêtes par mois
    // Rate Limite : 1000 par heures
}
