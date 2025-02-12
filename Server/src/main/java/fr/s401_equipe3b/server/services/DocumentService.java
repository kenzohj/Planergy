/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.services;

import fr.s401_equipe3b.server.entries.DocumentEntry;
import fr.s401_equipe3b.server.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Gestion des documents
 */
@Service
public class DocumentService {

    // Repository des documents
    @Autowired
    private DocumentRepository repository;

    /**
     * Récupérer les documents
     * @return Documents
     */
    public List<DocumentEntry> getAll() {
        return this.repository.findAll();
    }

    /**
     * Récupérer un document par son ID
     * @param id ID du document
     * @return Document
     */
    public DocumentEntry get(int id) {
        return this.repository.findByDocumentId(id);
    }

    /**
     * Créer un document
     * @param name Nom du document
     * @param restricted Document restreint
     * @param date Date du document
     * @param author Auteur du document
     * @param content Contenu du document
     * @return Document
     */
    public DocumentEntry create(String name, boolean restricted, String date, String author, String content) {
        final int id = this.repository.getNextDocumentId();
        final DocumentEntry document = new DocumentEntry(id, name, restricted, date, author, content);
        return this.repository.save(document);
    }
}
