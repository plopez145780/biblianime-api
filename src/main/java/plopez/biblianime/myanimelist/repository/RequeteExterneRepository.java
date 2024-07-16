package plopez.biblianime.myanimelist.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import plopez.biblianime.myanimelist.entity.ProviderExterne;
import plopez.biblianime.myanimelist.entity.RequeteExterne;

import java.time.LocalDateTime;

@Repository
public interface RequeteExterneRepository extends CrudRepository<RequeteExterne, Long> {

    //create
    //RequeteExterne save(RequeteExterne requeteExterne);

    // delete pour effacer l'historique dans le temps
    void deleteAllByDateBefore(LocalDateTime date);

    //count
    Long countByProviderAndDateBetween(ProviderExterne provider, LocalDateTime startDate, LocalDateTime endDate);


}
