/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories.custom;

/**
 * Interface pour les requêtes personnalisées sur les documents.
 */
public interface CustomDocumentRepository {

    /**
     * Récupère l'identifiant du prochain document.
     * @return l'identifiant du prochain document.
     */
    int getNextDocumentId();

}
