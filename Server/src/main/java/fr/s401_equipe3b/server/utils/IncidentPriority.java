/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.utils;

/**
 * Enumération des niveaux de priorité d'un incident
 */
public enum IncidentPriority {

    IMPORTANT, // Niveau de priorité : IMPORTANT
    CRITICAL; // Niveau de priorité : CRITIQUE

    /**
     * Méthode permettant de récupérer le niveau de priorité d'un incident
     * @param priority La priorité de l'incident
     * @return Le niveau de priorité de l'incident
     */
    public static IncidentPriority getPriority(String priority) {
        if (priority.equals("CRITICAL")) // Si la priorité est CRITICAL...
            return CRITICAL; // Retourne CRITICAL
        return IMPORTANT; // Sinon, retourne IMPORTANT
    }

}
