package plopez.biblianime.apiexterne.requeteexternelog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;

@Aspect
@Component
public class RequeteExterneAspect {

    @Autowired
    RequeteExterneService requeteExterneService;

    /**
     * Récupère l'annotation ProviderExterne à partir du ProceedingJoinPoint donné et renvoie le fournisseur spécifié dans l'annotation.
     *
     * @param proceedingJoinPoint le ProceedingJoinPoint à partir duquel récupérer l'annotation ProviderExterne
     * @return le fournisseur spécifié dans l'annotation ProviderExterne
     * @throws Exception si la classe spécifiée dans le ProceedingJoinPoint n'est pas annotée avec RequeteExterneProvider
     */
    private static ProviderExterne getProviderExterne(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        RequeteExterneProvider requeteExterneLog = proceedingJoinPoint.getTarget().getClass().getAnnotation(RequeteExterneProvider.class);

        if (requeteExterneLog == null) {
            throw new Exception("La class "
                    + proceedingJoinPoint.getTarget().getClass().getSimpleName()
                    + " n'est pas annotée avec RequeteExterneProvider");
        }
        return requeteExterneLog.provider();
    }

    @Around("@annotation(plopez.biblianime.apiexterne.requeteexternelog.RequeteExterneLog)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;

        ProviderExterne providerExterne = getProviderExterne(proceedingJoinPoint);
        verificationLimite(providerExterne);

        result = proceedingJoinPoint.proceed();

        if (result != null) {
            HttpResponse<String> httpResponse = (HttpResponse<String>) result;
            requeteExterneService.save(providerExterne, httpResponse.uri().toString());
        }


        return result;
    }

    private void verificationLimite(ProviderExterne providerExterne) throws Exception {
        int countedForCurrentMonthByProvider = requeteExterneService.countForCurrentMonthByProvider(providerExterne);
        if (countedForCurrentMonthByProvider > providerExterne.getRequestLimite()) {
            throw new Exception("La limite de requête pour le fournisseur :"
                    + providerExterne.getTitre()
                    + " est atteinte "
                    + countedForCurrentMonthByProvider
                    + " / "
                    + providerExterne.getRequestLimite()
            );
        }
    }
}
