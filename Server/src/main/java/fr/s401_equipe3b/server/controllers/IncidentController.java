/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.controllers;

import fr.s401_equipe3b.server.body.IncidentCreateBody;
import fr.s401_equipe3b.server.body.IncidentEditBody;
import fr.s401_equipe3b.server.body.IncidentUpdateStatusBody;
import fr.s401_equipe3b.server.entries.IncidentEntry;
import fr.s401_equipe3b.server.services.IncidentService;
import fr.s401_equipe3b.server.utils.IncidentPriority;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController // Déclaration de la classe comme étant un contrôleur REST pour SpringBoot
@CrossOrigin(origins = "*") // Autorisation de requêtes HTTP depuis n'importe quelle origine
@RequestMapping("/api/incident") // Déclaration du chemin de base pour les requêtes HTTP relative à la collection Incident
public class IncidentController {

    @Autowired // Injection de dépendance de la classe IncidentService
    private IncidentService service;

    /**
     * Méthode permettant de récupérer toutes les entrées de la collection Incident
     * @return La liste des entrées récupérées
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve all Incidents"), // Réponse HTTP 200, les entrées ont été trouvées
            @ApiResponse(responseCode = "404", description = "No data retrieved") // Réponse HTTP 404, aucune entrée n'a été trouvée
    })
    @GetMapping("all") // Déclaration de la méthode getAll comme étant une méthode HTTP GET
    public ResponseEntity<List<IncidentEntry>> getAll() {
        final List<IncidentEntry> entries = this.service.getAll(); // Récupération de toutes les entrées de la collection
        return entries.isEmpty() // Si la liste est vide...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entries); // Réponse HTTP 200, les entrées ont été trouvées
    }

    /**
     * Méthode permettant de récupérer une entrée de la collection Incident
     * @param id L'identifiant de l'entrée à récupérer
     * @return L'entrée correspondante à l'identifiant donné
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Incident found"), // Réponse HTTP 200, l'entrée a été trouvée
            @ApiResponse(responseCode = "404", description = "No Incident found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @GetMapping("id") // Déclaration de la méthode getById comme étant une méthode HTTP GET
    public ResponseEntity<IncidentEntry> getById(@RequestParam int id) {
        final IncidentEntry entry = this.service.get(id); // Récupération de l'entrée correspondante à l'identifiant donné
        return entry == null // Si l'entrée n'a pas été trouvée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été trouvée
    }

    /**
     * Méthode permettant de récupérer toutes les entrées de la collection Incident correspondant à une date donnée
     * @param date La date des entrées à récupérer
     * @return La liste des entrées récupérées
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Incidents found"), // Réponse HTTP 200, les entrées ont été trouvées
            @ApiResponse(responseCode = "404", description = "No Incidents found") // Réponse HTTP 404, aucune entrée n'a été trouvée
    })
    @GetMapping("date") // Déclaration de la méthode getByDate comme étant une méthode HTTP GET
    public ResponseEntity<List<IncidentEntry>> getByDate(@RequestParam String date) {
        final List<IncidentEntry> entries = this.service.getAll(date); // Récupération de toutes les entrées correspondant à la date donnée
        return entries.isEmpty() // Si la liste est vide...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entries); // Réponse HTTP 200, les entrées ont été trouvées
    }

    /**
     * Méthode permettant de créer une entrée dans la collection Incident
     * @param body Les informations de l'entrée à créer
     * @return L'entrée créée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Incident created") // Réponse HTTP 201, l'entrée a été créée
    })
    @PostMapping("create")
    public ResponseEntity<IncidentEntry> create(
            @RequestBody IncidentCreateBody body
    ) {
        // Création de la nouvelle entrée
        final IncidentEntry entry = this.service.create(body.date(), IncidentPriority.getPriority(body.priority()), body.author(), body.contacted(), body.description());
        return ResponseEntity.created(URI.create("/api/incident/id?id=" + entry.getIncidentId())).body(entry); // Réponse HTTP 201, la nouvelle entrée a été créée
    }

    /**
     * Méthode permettant de mettre à jour une entrée de la collection Incident
     * @param body Les informations de l'entrée à mettre à jour
     * @return L'entrée mise à jour
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Incident updated"), // Réponse HTTP 200, l'entrée a été mise à jour
            @ApiResponse(responseCode = "404", description = "Incident not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("/update") // Déclaration de la méthode update comme étant une méthode HTTP POST
    public ResponseEntity<IncidentEntry> update(
            @RequestBody IncidentEditBody body
    ) {
        final IncidentEntry updated = this.service.edit(body.id(), IncidentPriority.getPriority(body.priority()), body.contacted(), body.description()); // Mise à jour de l'entrée donnée
        return updated == null // Si l'entrée n'a pas été mise à jour...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(updated); // Réponse HTTP 200, l'entrée a été mise à jour
    }

    /**
     * Méthode permettant de supprimer une entrée de la collection Incident
     * @param idIncident L'identifiant de l'entrée à supprimer
     * @return Un booléen indiquant si l'entrée a été supprimée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Incident deleted"), // Réponse HTTP 200, l'entrée a été supprimée
            @ApiResponse(responseCode = "404", description = "Incident not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("/delete") // Déclaration de la méthode delete comme étant une méthode HTTP POST
    public ResponseEntity<Boolean> delete(
            @RequestBody int idIncident
    ) {
        return this.service.delete(idIncident) // Suppression de l'entrée correspondante à l'identifiant donné
                ? ResponseEntity.ok(true) // Si l'entrée a été supprimée, on renvoie une réponse HTTP 200
                : ResponseEntity.notFound().build(); // Sinon, on renvoie une réponse HTTP 404
    }

    /**
     * Méthode permettant de mettre à jour le statut d'une entrée de la collection Incident
     * @param body Les informations de l'entrée à mettre à jour
     * @return L'entrée mise à jour
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Incident updated"), // Réponse HTTP 200, l'entrée a été mise à jour
            @ApiResponse(responseCode = "404", description = "Incident not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("/updateStatus") // Déclaration de la méthode updateStatus comme étant une méthode HTTP POST
    public ResponseEntity<IncidentEntry> updateStatus(
            @RequestBody IncidentUpdateStatusBody body
    ) {
        final IncidentEntry entry = this.service.updateStatus(body.idIncident(), body.closed()); // Mise à jour du statut de l'entrée correspondante à l'identifiant donné
        return entry == null // Si l'entrée n'a pas été mise à jour...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été mise à jour
    }

}
