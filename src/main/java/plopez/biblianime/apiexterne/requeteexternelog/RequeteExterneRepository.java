package plopez.biblianime.apiexterne.requeteexternelog;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RequeteExterneRepository extends CrudRepository<RequeteExterne, Long> {

    //create
    //RequeteExterne save(RequeteExterne requeteExterne);

    // delete pour effacer l'historique dans le temps
    void deleteAllByDateBefore(LocalDateTime date);


    int countByProviderAndDateBetween(ProviderExterne provider, LocalDateTime startDate, LocalDateTime endDate);



}
