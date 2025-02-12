/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.body;

/**
 * Corps de la requête de mise à jour du seuil d'alerte d'une énergie.
 */
public record EnergyUpdateEnergyLimitBody(String name,
                                          double limit) {
}
