/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.entries;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * DocumentEntry
 */
@Document(collection = "document")
public class DocumentEntry {

    private int documentId; // Identifiant du document
    private String name; // Nom du document
    private boolean restricted; // Document restreint ou non
    private String date; // Date de création du document
    private String author; // Auteur du document
    private String content; // Contenu du document

    public DocumentEntry() {} // Constructeur vide pour SpringBoot

    /**
     * Constructeur
     * @param documentId Identifiant du document
     * @param name Nom du document
     * @param restricted Document restreint ou non
     * @param date Date de création du document
     * @param author Auteur du document
     * @param content Contenu du document
     */
    public DocumentEntry(int documentId, String name, boolean restricted, String date, String author, String content) {
        this.documentId = documentId;
        this.name = name;
        this.restricted = restricted;
        this.date = date;
        this.author = author;
        this.content = content;
    }

    /**
     * Getter
     * @return Identifiant du document
     */
    public int getDocumentId() {
        return this.documentId;
    }

    /**
     * Getter
     * @return Nom du document
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter
     * @return Document restreint ou non
     */
    public boolean isRestricted() {
        return this.restricted;
    }

    /**
     * Getter
     * @return Date de création du document
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Getter
     * @return Auteur du document
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Getter
     * @return Contenu du document
     */
    public String getContent() {
        return this.content;
    }

}
