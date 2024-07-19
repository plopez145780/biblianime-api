package plopez.biblianime.apiexterne.requeteexternelog;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RequeteExterneServiceImpl implements RequeteExterneService {

    @Autowired
    private RequeteExterneRepository requeteExterneRepository;

    @Override
    public RequeteExterne save(ProviderExterne providerExterne, String url) {
        return requeteExterneRepository.save(
                new RequeteExterne(providerExterne, url)
        );
    }

    @Override
    public boolean deleteAllByDateBefore(LocalDateTime date) {
        return requeteExterneRepository.deleteAllByDateBefore(date);
    }

    /**
     * Compte le nombre de requêtes effectuées vers un fournisseur dans le mois en cours.
     *
     * @param provider le fournisseur pour lequel compter les requêtes
     * @return le nombre de requêtes effectuées vers le fournisseur dans le mois en cours
     */
    @Override
    public int countForCurrentMonthByProvider(ProviderExterne provider) {
        LocalDate today = LocalDate.now();
        return requeteExterneRepository.countByProviderAndDateBetween(
                provider,
                LocalDateTime.of(today.getYear(), today.getMonthValue(), 1, 0, 0, 0),
                LocalDateTime.of(today.getYear(), today.getMonthValue(), today.lengthOfMonth(), 23, 59, 59));
    }
}
