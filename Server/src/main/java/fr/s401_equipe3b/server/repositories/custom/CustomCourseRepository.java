/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories.custom;

/**
 * Interface pour les requêtes personnalisées sur les formations.
 */
public interface CustomCourseRepository {

        /**
         * Récupère l'identifiant de la prochaine formation.
         * @return l'identifiant de la prochaine formation.
         */
        int getNextCourseId();

}
