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
 * Implémentation de l'interface CustomDocumentRepository.
 */
@Repository
public class CustomDocumentRepositoryImpl implements CustomDocumentRepository {

    // Injection de dépendance de l'opération MongoDB.
    @Autowired
    private MongoOperations mongoOperations;

    /**
     * Récupère l'identifiant du prochain document.
     * @return L'identifiant du prochain document.
     */
    public int getNextDocumentId() {
        return AutoIncrementer.getNextId(mongoOperations, "document");
    }

}
