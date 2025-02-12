/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories.custom;

/**
 * Interface pour les requêtes personnalisées sur les clients.
 */
public interface CustomCustomerRepository {

    /**
     * Récupère l'identifiant du prochain client.
     * @return l'identifiant du prochain client.
     */
    int getNextCustomerId();

}
