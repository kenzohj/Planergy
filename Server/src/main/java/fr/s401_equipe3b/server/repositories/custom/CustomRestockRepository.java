/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories.custom;

/**
 * Interface pour les requêtes personnalisées sur les réapprovisionnements.
 */
public interface CustomRestockRepository {

    /**
     * Récupère l'identifiant du prochain réapprovisionnement.
     * @return l'identifiant du prochain réapprovisionnement.
     */
    int getNextRestockId();

}
