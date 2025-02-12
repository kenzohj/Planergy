/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories.custom;

import fr.s401_equipe3b.server.utils.AutoIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

/**
 * Implémentation de l'interface CustomIncidentRepository.
 */
@Repository
public class CustomIncidentRepositoryImpl implements CustomIncidentRepository {

    // Injection de dépendance de l'opération MongoDB.
    @Autowired
    private MongoOperations mongoOperations;

    /**
     * Récupère l'identifiant du prochain incident.
     * @return L'identifiant du prochain incident.
     */
    @Override
    public int getNextIncidentId() {
        return AutoIncrementer.getNextId(mongoOperations, "incident");
    }

}
