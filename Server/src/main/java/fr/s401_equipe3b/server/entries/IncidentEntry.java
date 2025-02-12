/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.entries;

import fr.s401_equipe3b.server.utils.IncidentPriority;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * IncidentEntry
 */
@Document(collection = "incident")
public class IncidentEntry {

    private int incidentId; // Identifiant de l'incident
    private String date; // Date de l'incident
    private IncidentPriority priority; // Priorité de l'incident
    private String author; // Auteur de l'incident
    private String contacted; // Personne contactée
    private String solutionDate; // Date de résolution de l'incident
    private String description; // Description de l'incident
    private String solution; // Solution de l'incident
    private boolean closed; // Statut de l'incident

    public IncidentEntry() {} // Constructeur vide pour SpringBoot

    /**
     * Constructeur
     * @param incidentId Identifiant de l'incident
     * @param date Date de l'incident
     * @param priority Priorité de l'incident
     * @param author Auteur de l'incident
     * @param contacted Personne contactée
     * @param solutionDate Date de résolution de l'incident
     * @param description Description de l'incident
     * @param solution Solution de l'incident
     * @param closed Statut de l'incident
     */
    public IncidentEntry(int incidentId, String date, IncidentPriority priority, String author, String contacted, String solutionDate, String description, String solution, boolean closed) {
        this.incidentId = incidentId;
        this.date = date;
        this.priority = priority;
        this.author = author;
        this.contacted = contacted;
        this.solutionDate = solutionDate;
        this.description = description;
        this.solution = solution;
        this.closed = closed;
    }

    /**
     * Getter
     * @return Identifiant de l'incident
     */
    public int getIncidentId() {
        return this.incidentId;
    }

    /**
     * Getter
     * @return Date de l'incident
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Getter
     * @return Priorité de l'incident
     */
    public IncidentPriority getPriority() {
        return this.priority;
    }

    /**
     * Getter
     * @return Auteur de l'incident
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Getter
     * @return Personne contactée
     */
    public String getContacted() {
        return this.contacted;
    }

    /**
     * Getter
     * @return Date de résolution de l'incident
     */
    public String getSolutionDate() {
        return this.solutionDate;
    }

    /**
     * Getter
     * @return Description de l'incident
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter
     * @return Solution de l'incident
     */
    public String getSolution() {
        return this.solution;
    }

    /**
     * Getter
     * @return Statut de l'incident
     */
    public boolean isClosed() {
        return this.closed;
    }

}
