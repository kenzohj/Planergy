/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories;

import fr.s401_equipe3b.server.entries.IncidentEntry;
import fr.s401_equipe3b.server.repositories.custom.CustomIncidentRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface IncidentRepository
 */
@Repository
public interface IncidentRepository extends MongoRepository<IncidentEntry, String>, CustomIncidentRepository {

    /**
     * Vérifie si l'entrée d'incident existe par l'identifiant
     * @param incidentId Identifiant
     * @return boolean
     */
    IncidentEntry findByIncidentId(int incidentId);

    /**
     * Recherche les entrées d'incident par la date
     * @param date Date
     * @return List<IncidentEntry>
     */
    List<IncidentEntry> findByDate(String date);

    /**
     * Vérifie si l'entrée d'incident existe par l'identifiant
     * @param incidentId Identifiant
     * @return boolean
     */
    boolean existsByIncidentId(int incidentId);

    /**
     * Supprime l'entrée d'incident par l'identifiant
     * @param incidentId Identifiant
     */
    void deleteByIncidentId(int incidentId);

}
