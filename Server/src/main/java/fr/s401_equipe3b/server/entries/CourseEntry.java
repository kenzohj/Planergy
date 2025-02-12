/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.entries;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * CourseEntry
 */
@Document(collection = "course")
public class CourseEntry {

    private int courseId; // Identifiant de la formation
    private String mail; // Adresse mail de l'employé
    private String type; // Type de la formation
    private String startDate; // Date de début de la formation
    private String endDate; // Date de fin de la formation

    public CourseEntry() {} // Constructeur vide pour SpringBoot

    /**
     * Constructeur
     * @param courseId Identifiant de la formation
     * @param mail Adresse mail de l'employé
     * @param type Type de la formation
     * @param startDate Date de début de la formation
     * @param endDate Date de fin de la formation
     */
    public CourseEntry(int courseId, String mail, String type, String startDate, String endDate) {
        this.courseId = courseId;
        this.mail = mail;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Getter
     * @return Identifiant de la formation
     */
    public int getCourseId() {
        return this.courseId;
    }

    /**
     * Getter
     * @return Adresse mail de l'employé
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * Getter
     * @return Type de la formation
     */
    public String getType() {
        return this.type;
    }

    /**
     * Getter
     * @return Date de début de la formation
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * Getter
     * @return Date de fin de la formation
     */
    public String getEndDate() {
        return this.endDate;
    }

}
