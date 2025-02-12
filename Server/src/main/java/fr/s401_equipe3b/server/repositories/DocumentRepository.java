/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories;

import fr.s401_equipe3b.server.entries.DocumentEntry;
import fr.s401_equipe3b.server.repositories.custom.CustomDocumentRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface DocumentRepository
 */
@Repository
public interface DocumentRepository extends MongoRepository<DocumentEntry, String>, CustomDocumentRepository {

    /**
     * Trouve l'entrée de document par l'identifiant de document
     * @param documentId Identifiant de document
     * @return DocumentEntry
     */
    DocumentEntry findByDocumentId(int documentId);

}
