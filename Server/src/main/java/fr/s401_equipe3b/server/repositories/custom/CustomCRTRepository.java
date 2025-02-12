/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories.custom;

/**
 * Interface pour les requêtes personnalisées sur les transactions.
 */
public interface CustomCRTRepository {

    /**
     * Récupère l'identifiant de la prochaine transaction.
     * @return l'identifiant de la prochaine transaction.
     */
    int getNextTransactionId();

}
