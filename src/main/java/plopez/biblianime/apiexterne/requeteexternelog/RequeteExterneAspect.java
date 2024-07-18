package plopez.biblianime.apiexterne.requeteexternelog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Aspect
@Component
public class RequeteExterneAspect {

    @Autowired
    RequeteExterneRepository requeteExterneRepository;

    @Around("@annotation(plopez.biblianime.apiexterne.requeteexternelog.RequeteExterneLog)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;

        ProviderExterne providerExterne = getProviderExterne(proceedingJoinPoint);

        /* compte que l'on est sous la limite de requetes possible */
        if (getRequestAccountByProvider(providerExterne) > providerExterne.getRequestLimite()) {
            throw new Exception("La limite de requête pour le fournisseur :"
                    + providerExterne.getTitre()
                    + " est atteinte "
                    + getRequestAccountByProvider(providerExterne)
                    + " / "
                    + providerExterne.getRequestLimite()
            );
        }

        // execute l'appel
        result = proceedingJoinPoint.proceed();
        HttpResponse<String> httpResponse = (HttpResponse<String>) result;

        // Enregistre la requête
        requeteExterneRepository.save(
                new RequeteExterne(
                        providerExterne,
                        httpResponse.uri().toString()
                ));

        return result;
    }


    private static ProviderExterne getProviderExterne(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        RequeteExterneProvider requeteExterneLog = proceedingJoinPoint.getTarget().getClass().getAnnotation(RequeteExterneProvider.class);

        if (requeteExterneLog == null) {
            throw new Exception("La class "
                    + proceedingJoinPoint.getTarget().getClass().getSimpleName()
                    + " n'est pas annotée avec RequeteExterneProvider");
        }
        return requeteExterneLog.provider();
    }

    /**
     * Récupère le nombre de requêtes effectuées par un fournisseur durant le mois en cours.
     *
     * @param providerExterne le fournisseur pour lequel récupérer le nombre de requêtes
     * @return le nombre de requêtes effectuées par le fournisseur durant le mois en cours
     */
    private int getRequestAccountByProvider(ProviderExterne providerExterne) {
        LocalDate today = LocalDate.now();
        return requeteExterneRepository.countByProviderAndDateBetween(
                providerExterne,
                LocalDateTime.of(today.getYear(), today.getMonthValue(), 1, 0, 0, 0),
                LocalDateTime.of(today.getYear(), today.getMonthValue(), today.lengthOfMonth(), 23, 59, 59));
    }
}
