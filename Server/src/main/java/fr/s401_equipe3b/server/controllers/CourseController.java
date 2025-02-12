/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.controllers;

import fr.s401_equipe3b.server.body.CourseCreateBody;
import fr.s401_equipe3b.server.body.CourseEditBody;
import fr.s401_equipe3b.server.entries.CourseEntry;
import fr.s401_equipe3b.server.services.CourseService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController // Déclaration de la classe comme étant un contrôleur REST pour SpringBoot
@CrossOrigin(origins = "*") // Autorisation de requêtes HTTP depuis n'importe quelle origine
@RequestMapping("/api/course") // Déclaration du chemin de base pour les requêtes HTTP relative à la collection Course
public class CourseController {

    @Autowired // Injection de dépendance de la classe CourseService
    private CourseService service;

    /**
     * Méthode permettant de récupérer toutes les entrées de la collection Course
     * @return La liste des entrées récupérées
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Courses found"), // Réponse HTTP 200, les entrées ont été trouvées
            @ApiResponse(responseCode = "404", description = "No courses found") // Réponse HTTP 404, aucune entrée n'a été trouvée
    })
    @GetMapping("/all") // Déclaration de la méthode getAll comme étant une méthode HTTP GET
    public ResponseEntity<List<CourseEntry>> getAllCourses() {
        final List<CourseEntry> courses = this.service.getAll(); // Récupération de toutes les entrées de la collection Course
        return courses.isEmpty() // Si la liste est vide...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(courses); // Réponse HTTP 200, les entrées ont été trouvées
    }

    /**
     * Méthode permettant de récupérer une entrée de la collection Course
     * @param courseId L'identifiant de l'entrée à récupérer
     * @return L'entrée correspondante à l'identifiant donné
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course found"), // Réponse HTTP 200, l'entrée a été trouvée
            @ApiResponse(responseCode = "404", description = "Course not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @GetMapping("/entry")// Déclaration de la méthode getCourseEntry comme étant une méthode HTTP GET
    public ResponseEntity<CourseEntry> getCourseEntry(
            @RequestParam int courseId
    ) {
        final CourseEntry course = this.service.get(courseId); // Récupération de l'entrée correspondante à l'identifiant donné
        return course == null ? // Si l'entrée n'a pas été trouvée...
                ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(course); // Réponse HTTP 200, l'entrée a été trouvée
    }

    /**
     * Méthode permettant de créer une entrée dans la collection Course
     * @param body Les informations nécessaires à la création de l'entrée
     * @return L'entrée créée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Course created"), // Réponse HTTP 201, l'entrée a été créée
    })
    @PostMapping("/create") // Déclaration de la méthode createCourse comme étant une méthode HTTP POST
    public ResponseEntity<CourseEntry> createCourse(
            @RequestBody CourseCreateBody body
    ) {
        final CourseEntry createdCourse = this.service.create(body.type(), body.mail(), body.startDate(), body.endDate()); // Création de l'entrée
        return ResponseEntity.created(URI.create("/api/course/entry?courseId=" + createdCourse.getCourseId())).body(createdCourse); // Réponse HTTP 201, l'entrée a été créée
    }

    /**
     * Méthode permettant de modifier une entrée de la collection Course
     * @param body Les informations nécessaires à la modification de l'entrée
     * @return L'entrée modifiée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course updated"), // Réponse HTTP 200, l'entrée a été modifiée
            @ApiResponse(responseCode = "404", description = "Course not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("/update") // Déclaration de la méthode updateCourse comme étant une méthode HTTP POST
    public ResponseEntity<CourseEntry> updateCourse(
            @RequestBody CourseEditBody body
    ) {
        final CourseEntry updatedCourse = this.service.edit(body.id(), body.type(), body.startDate(), body.endDate()); // Mise à jour de l'entrée
        return updatedCourse == null ? // Si l'entrée n'a pas été trouvée...
                ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(updatedCourse); // Réponse HTTP 200, l'entrée a été modifiée
    }

    /**
     * Méthode permettant de supprimer une entrée de la collection Course
     * @param courseId L'identifiant de l'entrée à supprimer
     * @return Un booléen indiquant si l'entrée a été supprimée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course deleted"), // Réponse HTTP 200, l'entrée a été supprimée
            @ApiResponse(responseCode = "404", description = "Course not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("/delete") // Déclaration de la méthode deleteCourse comme étant une méthode HTTP POST
    public ResponseEntity<Boolean> deleteCourse(
            @RequestBody int courseId
    ) {
        return !this.service.delete(courseId) // Suppression de l'entrée correspondante à l'identifiant donné
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(true); // Réponse HTTP 200, l'entrée a été supprimée
    }

}
