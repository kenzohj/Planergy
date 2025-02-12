/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.services;

import fr.s401_equipe3b.server.entries.IncidentEntry;
import fr.s401_equipe3b.server.repositories.IncidentRepository;
import fr.s401_equipe3b.server.utils.IncidentPriority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Gestion des incidents
 */
@Service
public class IncidentService {

    // Repository des incidents
    @Autowired
    private IncidentRepository repository;

    /**
     * Récupérer les incidents
     * @return Incidents
     */
    public List<IncidentEntry> getAll() {
        return repository.findAll();
    }

    /**
     * Récupérer un incident par son identifiant
     * @param id Identifiant de l'incident
     * @return Incident
     */
    public IncidentEntry get(int id) {
        return repository.findByIncidentId(id);
    }

    /**
     * Récupérer les incidents par date
     * @param date Date des incidents
     * @return Incidents
     */
    public List<IncidentEntry> getAll(String date) {
        return repository.findByDate(date);
    }

    /**
     * Créer un incident
     * @param date Date de l'incident
     * @param priority Priorité de l'incident
     * @param author Auteur de l'incident
     * @param contacted Contacté
     * @param description Description de l'incident
     * @return Incident
     */
    public IncidentEntry create(String date, IncidentPriority priority, String author, String contacted, String description) {
        final IncidentEntry incident = new IncidentEntry(this.repository.getNextIncidentId(), date, priority, author, contacted, "-", description, "-", false);
        return repository.save(incident);
    }

    /**
     * Éditer un incident
     * @param id Identifiant de l'incident
     * @param priority Priorité de l'incident
     * @param contacted Contacté
     * @param description Description de l'incident
     * @return Incident
     */
    public IncidentEntry edit(int id, IncidentPriority priority, String contacted, String description) {
        final IncidentEntry incident = repository.findByIncidentId(id);
        if(incident == null) return null;
        repository.deleteByIncidentId(id);
        return repository.save(new IncidentEntry(id, incident.getDate(), priority, incident.getAuthor(), contacted, incident.getSolutionDate(), description, incident.getSolution(), incident.isClosed()));
    }

    /**
     * Supprimer un incident par son identifiant
     * @param idIncident Identifiant de l'incident
     * @return Succès
     */
    public boolean delete(int idIncident) {
        if(!repository.existsByIncidentId(idIncident)) return false;
        this.repository.deleteByIncidentId(idIncident);
        return true;
    }

    /**
     * Mettre à jour le statut d'un incident
     * @param id Identifiant de l'incident
     * @param closed Fermé
     * @return Incident
     */
    public IncidentEntry updateStatus(int id, boolean closed) {
        final IncidentEntry incident = repository.findByIncidentId(id);
        if(incident == null) return null;
        repository.deleteByIncidentId(id);
        return repository.save(new IncidentEntry(id, incident.getDate(), incident.getPriority(), incident.getAuthor(), incident.getContacted(), incident.getSolutionDate(), incident.getDescription(), incident.getSolution(), closed));
    }

}
