package plopez.biblianime.apiexterne.requeteexternelog;

import java.time.LocalDateTime;

public interface RequeteExterneService {

    RequeteExterne save(ProviderExterne provider, String url);

    boolean deleteAllByDateBefore(LocalDateTime date);

    int countForCurrentMonthByProvider(ProviderExterne provider);
}
