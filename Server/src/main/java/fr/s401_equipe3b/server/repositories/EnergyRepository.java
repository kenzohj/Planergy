/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories;

import fr.s401_equipe3b.server.entries.EnergyEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface EnergyRepository
 */
@Repository
public interface EnergyRepository extends MongoRepository<EnergyEntry, String> {

    /**
     * Vérifie si l'entrée d'énergie existe par le nom
     * @param name Nom
     * @return boolean
     */
    boolean existsByName(String name);

    /**
     * Trouve l'entrée d'énergie par le nom
     * @param name Nom
     * @return EnergyEntry
     */
    EnergyEntry findByName(String name);

    /**
     * Supprime l'entrée d'énergie par le nom
     * @param name Nom
     */
    void deleteByName(String name);

}
