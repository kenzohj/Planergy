/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories.custom;

/**
 * Interface pour les requêtes personnalisées sur les incidents.
 */
public interface CustomIncidentRepository {

    /**
     * Récupère l'identifiant du prochain incident.
     * @return l'identifiant du prochain incident.
     */
    int getNextIncidentId();

}
