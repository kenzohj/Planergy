/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.body;

import java.util.Map;

/**
 * Corps de la requête de création d'un réapprovisionnement.
 */
public record RestockCreateBody(String userId,
                                String date,
                                Map<String, Integer> order,
                                double amount) {
}
