/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories;

import fr.s401_equipe3b.server.entries.RestockEntry;
import fr.s401_equipe3b.server.repositories.custom.CustomRestockRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface RestockRepository
 */
@Repository
public interface RestockRepository extends MongoRepository<RestockEntry, String>, CustomRestockRepository {

    /**
     * Vérifie si l'entrée de réapprovisionnement existe par l'ID
     * @param id ID
     * @return boolean
     */
    RestockEntry findByRestockId(int id);

    /**
     * Supprime l'entrée de réapprovisionnement par l'ID
     * @param id ID
     */
    void deleteByRestockId(int id);

}
