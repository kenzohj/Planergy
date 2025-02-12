/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories;

import fr.s401_equipe3b.server.entries.CourseEntry;
import fr.s401_equipe3b.server.repositories.custom.CustomCourseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface CourseRepository
 */
@Repository
public interface CourseRepository extends MongoRepository<CourseEntry, String>, CustomCourseRepository {

    /**
     * Trouve l'entrée de cours par l'identifiant de cours
     * @param courseId Identifiant de cours
     * @return CourseEntry
     */
    CourseEntry findByCourseId(int courseId);

    /**
     * Supprime l'entrée de cours par l'identifiant de cours
     * @param courseId Identifiant de cours
     */
    void deleteByCourseId(int courseId);

}
