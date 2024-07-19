package plopez.biblianime.apiexterne.requeteexternelog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProviderExterne {
    MYANIMELIST("myanimelist", 100),// Rate Limite : 1000 par heures
    WEBTOON("webtoon", 500);

    private final String titre;
    private final int requestLimite; // Nombre de requêtes par mois
}
