/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.body;

/**
 * Corps de la requête de mise à jour d'un produit.
 */
public record ProductUpdateBody(int productId,
                                String name,
                                double newPrice,
                                double newWeight,
                                int newQuantity,
                                double newLimit) {
}
