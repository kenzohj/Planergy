/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.controllers;

import fr.s401_equipe3b.server.body.RestockCreateBody;
import fr.s401_equipe3b.server.body.RestockUpdateArrivalDateBody;
import fr.s401_equipe3b.server.body.RestockUpdateStatusBody;
import fr.s401_equipe3b.server.entries.RestockEntry;
import fr.s401_equipe3b.server.services.RestockService;
import fr.s401_equipe3b.server.utils.RestockStatus;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController // Déclaration de la classe comme étant un contrôleur REST pour SpringBoot
@CrossOrigin(origins = "*") // Autorisation de requêtes HTTP depuis n'importe quelle origine
@RequestMapping("/api/restock") // Déclaration du chemin de base pour les requêtes HTTP relative à la collection Restock
public class RestockController {

    @Autowired // Injection de dépendance de la classe RestockService
    private RestockService service;

    /**
     * Méthode permettant de récupérer toutes les entrées de la collection Restock
     * @return La liste des entrées récupérées
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All restocks found"), // Réponse HTTP 200, les entrées ont été trouvées
            @ApiResponse(responseCode = "404", description = "No restocks found") // Réponse HTTP 404, aucune entrée n'a été trouvée
    })
    @GetMapping("/all") // Déclaration de la méthode getAll comme étant une méthode HTTP GET
    public ResponseEntity<List<RestockEntry>> getAll() {
        return this.service.getAll().isEmpty() // Si la liste est vide...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(this.service.getAll()); // Réponse HTTP 200, les entrées ont été trouvées
    }

    /**
     * Méthode permettant de récupérer une entrée de la collection Restock
     * @param id L'identifiant de l'entrée à récupérer
     * @return L'entrée correspondante à l'identifiant donné
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restock found"), // Réponse HTTP 200, l'entrée a été trouvée
            @ApiResponse(responseCode = "404", description = "Restock not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @GetMapping("/id") // Déclaration de la méthode getById comme étant une méthode HTTP GET
    public ResponseEntity<RestockEntry> getById(
            @RequestParam int id
    ) {
        final RestockEntry entry = this.service.get(id); // Récupération de l'entrée correspondante à l'identifiant donné
        return entry == null // Si l'entrée n'a pas été trouvée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été trouvée
    }

    /**
     * Méthode permettant de créer une entrée dans la collection Restock
     * @param body Les informations de la nouvelle entrée
     * @return La nouvelle entrée créée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Restock created") // Réponse HTTP 201, l'entrée a été créée
    })
    @PostMapping("/create") // Déclaration de la méthode create comme étant une méthode HTTP POST
    public ResponseEntity<RestockEntry> create(
            @RequestBody RestockCreateBody body
    ) {
        final RestockEntry entry = this.service.create(body.userId(), body.date(), body.order(), body.amount()); // Création de la nouvelle entrée
        return ResponseEntity.created(URI.create("/api/restock/id?id=" + entry.getRestockId())).body(entry); // Réponse HTTP 201, la nouvelle entrée a été créée
    }

    /**
     * Méthode permettant de supprimer une entrée de la collection Restock
     * @param id L'identifiant de l'entrée à supprimer
     * @return L'entrée supprimée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restock deleted"), // Réponse HTTP 200, l'entrée a été supprimée
            @ApiResponse(responseCode = "404", description = "Restock not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("/delete") // Déclaration de la méthode delete comme étant une méthode HTTP POST
    public ResponseEntity<RestockEntry> delete(
            @RequestBody int id
    ) {
        final RestockEntry entry = this.service.delete(id); // Suppression de l'entrée correspondante à l'identifiant donné
        return entry == null // Si l'entrée n'a pas été supprimée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été supprimée
    }

    /**
     * Méthode permettant de mettre à jour le statut d'une entrée de la collection Restock
     * @param body Les informations de l'entrée à mettre à jour
     * @return L'entrée mise à jour
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restock updated"), // Réponse HTTP 200, l'entrée a été mise à jour
            @ApiResponse(responseCode = "404", description = "Restock not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("/update/status") // Déclaration de la méthode updateStatus comme étant une méthode HTTP POST
    public ResponseEntity<RestockEntry> updateStatus(
            @RequestBody RestockUpdateStatusBody body
    ) {
        final RestockEntry entry = this.service.updateStatus(body.id(), RestockStatus.valueOf(body.status())); // Mise à jour du statut de l'entrée
        return entry == null // Si l'entrée n'a pas été mise à jour...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été mise à jour
    }

    /**
     * Méthode permettant de mettre à jour la date d'arrivée d'une entrée de la collection Restock
     * @param body Les informations de l'entrée à mettre à jour
     * @return L'entrée mise à jour
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restock updated"), // Réponse HTTP 200, l'entrée a été mise à jour
            @ApiResponse(responseCode = "404", description = "Restock not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("/update/amount") // Déclaration de la méthode updateAmount comme étant une méthode HTTP POST
    public ResponseEntity<RestockEntry> updateArrivalDate(
            @RequestBody RestockUpdateArrivalDateBody body
    ) {
        final RestockEntry entry = this.service.updateArrivalDate(body.id(), body.arrivalDate()); // Mise à jour du statut de l'entrée
        return entry == null // Si l'entrée n'a pas été mise à jour...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été mise à jour
    }

}
