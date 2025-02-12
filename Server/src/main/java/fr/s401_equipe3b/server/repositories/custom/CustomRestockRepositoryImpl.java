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
 * Implémentation de l'interface CustomRestockRepository.
 */
@Repository
public class CustomRestockRepositoryImpl implements CustomRestockRepository {

    // Injection de dépendance de l'opération MongoDB.
    @Autowired
    private MongoOperations mongoOperations;

    /**
     * Récupère l'identifiant du prochain réapprovisionnement.
     * @return L'identifiant du prochain réapprovisionnement.
     */
    @Override
    public int getNextRestockId() {
        return AutoIncrementer.getNextId(mongoOperations, "restock");
    }

}
