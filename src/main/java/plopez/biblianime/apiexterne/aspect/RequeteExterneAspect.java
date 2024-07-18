package plopez.biblianime.apiexterne.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plopez.biblianime.apiexterne.annotation.RequeteExterneLog;
import plopez.biblianime.apiexterne.entity.ProviderExterne;
import plopez.biblianime.apiexterne.entity.RequeteExterne;
import plopez.biblianime.apiexterne.repository.RequeteExterneRepository;

import java.lang.reflect.Method;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Aspect
@Component
public class RequeteExterneAspect {

    @Autowired
    RequeteExterneRepository requeteExterneRepository;

    //@Around("execution(* plopez.biblianime.apiexterne.*.provider.*.*(..))")
    @Around("@annotation(plopez.biblianime.apiexterne.annotation.RequeteExterneLog)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        //ProviderExterne providerExterne = ProviderExterne.MYANIMELIST;
        //(ProviderExterne) proceedingJoinPoint.getTarget().getProviderExterne();

        // Method annotation
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        RequeteExterneLog requeteExterneLog = method.getAnnotation(RequeteExterneLog.class);
        ProviderExterne providerExterne = requeteExterneLog.provider();

        /* compte que l'on est sous la limite de requetes possible */
        if (getRequestAccountByProvider(providerExterne) <= ProviderExterne.MYANIMELIST.getRequestLimite()) {
            // execute l'appel

            result = proceedingJoinPoint.proceed();

            HttpResponse<String> seasonalAnimes = (HttpResponse<String>) result;

            String url = seasonalAnimes.uri().toString();

            // Enregistre l'URL de l'appel
            RequeteExterne requeteExterne = new RequeteExterne(
                    providerExterne,
                    url
            );
            requeteExterneRepository.save(requeteExterne);
        }
        return result;
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
