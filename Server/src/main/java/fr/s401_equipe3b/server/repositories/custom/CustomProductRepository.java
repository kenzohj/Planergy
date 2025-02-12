/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories.custom;

/**
 * Interface pour les requêtes personnalisées sur les produits.
 */
public interface CustomProductRepository {

        /**
         * Récupère l'identifiant du prochain produit.
         * @return l'identifiant du prochain produit.
         */
        int getNextProductId();

}
