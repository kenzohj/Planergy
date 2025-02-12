/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.services;

import fr.s401_equipe3b.server.entries.CourseEntry;
import fr.s401_equipe3b.server.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Gestion des formations
 */
@Service
public class CourseService {

    /**
     * Repository des formations
     */
    @Autowired
    private CourseRepository repository;

    /**
     * Récupérer toutes les formations
     * @return Formations
     */
    public List<CourseEntry> getAll() {
        return this.repository.findAll();
    }

    /**
     * Récupérer une formation
     * @param courseId ID de la formation
     * @return Formation
     */
    public CourseEntry get(int courseId) {
        return this.repository.findByCourseId(courseId);
    }

    /**
     * Créer une formation
     * @param type Type
     * @param startDate Date de début
     * @param endDate Date de fin
     * @return Formation créée
     */
    public CourseEntry create(String type, String mail, String startDate, String endDate) {
        final int courseId = this.repository.getNextCourseId();
        final CourseEntry course = new CourseEntry(courseId, mail, type, startDate, endDate);
        return this.repository.save(course);
    }

    /**
     * Modifier une formation
     * @param id ID de la formation
     * @param type Type
     * @param startDate Date de début
     * @param endDate Date de fin
     * @return Formation modifiée
     */
    public CourseEntry edit(int id, String type, String startDate, String endDate) {
        final CourseEntry course = this.get(id);
        if(course == null) return null;
        this.delete(id);
        return this.repository.save(new CourseEntry(id, course.getMail(), type, startDate, endDate));
    }

    /**
     * Supprimer une formation
     * @param courseId ID de la formation
     */
    public boolean delete(int courseId) {
        if(this.get(courseId) == null) return false;
        this.repository.deleteByCourseId(courseId);
        return true;
    }

}
