/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.body;

/**
 * Corps de la requête de création d'un produit.
 */
public record ProductCreateBody(String name,
                                double price,
                                double weight,
                                int quantity,
                                double limit) {
}
