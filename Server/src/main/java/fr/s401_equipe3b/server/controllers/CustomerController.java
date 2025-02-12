/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.controllers;

import fr.s401_equipe3b.server.body.CustomerAddPointsBody;
import fr.s401_equipe3b.server.body.CustomerCreateBody;
import fr.s401_equipe3b.server.body.CustomerEditBody;
import fr.s401_equipe3b.server.entries.CustomerEntry;
import fr.s401_equipe3b.server.services.CustomerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController // Déclaration de la classe comme étant un contrôleur REST pour SpringBoot
@CrossOrigin(origins = "*") // Autorisation de requêtes HTTP depuis n'importe quelle origine
@RequestMapping("/api/customer") // Déclaration du chemin de base pour les requêtes HTTP relative à la collection Customer
public class CustomerController {

    @Autowired // Injection de dépendance de la classe CustomerService
    private CustomerService service;

    /**
     * Méthode permettant de récupérer toutes les entrées de la collection Customer
     * @return La liste des entrées récupérées
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve all customers"), // Réponse HTTP 200, les entrées ont été trouvées
            @ApiResponse(responseCode = "404", description = "No data retrieved"), // Réponse HTTP 404, aucune entrée n'a été trouvée
    })
    @GetMapping("all") // Déclaration de la méthode getAll comme étant une méthode HTTP GET
    public ResponseEntity<List<CustomerEntry>> getAll() {
        final List<CustomerEntry> entries = this.service.getAll(); // Récupération de toutes les entrées de la collection Customer
        return entries.isEmpty() // Si la liste est vide...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entries); // Réponse HTTP 200, les entrées ont été trouvées
    }

    /**
     * Méthode permettant de récupérer une entrée de la collection Customer
     * @param id L'identifiant de l'entrée à récupérer
     * @return L'entrée correspondante à l'identifiant donné
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer found"), // Réponse HTTP 200, l'entrée a été trouvée
            @ApiResponse(responseCode = "404", description = "No customer found"), // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @GetMapping("id") // Déclaration de la méthode getById comme étant une méthode HTTP GET
    public ResponseEntity<CustomerEntry> getById(
            @RequestParam int id
    ) {
        final CustomerEntry entry = this.service.getById(id); // Récupération de l'entrée correspondante à l'identifiant donné
        return entry == null // Si l'entrée n'a pas été trouvée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été trouvée
    }

    /**
     * Méthode permettant de créer une entrée dans la collection Customer
     * @param body Les informations de l'entrée à créer
     * @return L'entrée créée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer created"), // Réponse HTTP 201, l'entrée a été créée
            @ApiResponse(responseCode = "409", description = "Customer already exists"), // Réponse HTTP 409, une entrée avec le même mail existe déjà
    })
    @PostMapping("create") // Déclaration de la méthode create comme étant une méthode HTTP POST
    public ResponseEntity<CustomerEntry> create(
            @RequestBody CustomerCreateBody body
    ) {
        final CustomerEntry entry = this.service.create(body.mail(), body.firstName(), body.lastName()); // Création de l'entrée avec les informations données
        return entry == null
                ? ResponseEntity.status(409).build() // Réponse HTTP 409, une entrée avec le même mail existe déjà
                : ResponseEntity.created(URI.create("/api/customer/id?id=" + entry.getCustomerId())).body(entry); // Réponse HTTP 201, l'entrée a été créée
    }

    /**
     * Méthode permettant de mettre à jour une entrée de la collection Customer
     * @param body Les informations de l'entrée à mettre à jour
     * @return L'entrée mise à jour
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer updated"), // Réponse HTTP 200, l'entrée a été mise à jour
            @ApiResponse(responseCode = "404", description = "No customer found"), // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("addPoints") // Déclaration de la méthode addPoints comme étant une méthode HTTP POST
    public ResponseEntity<CustomerEntry> addPoints(
            @RequestBody CustomerAddPointsBody body
    ) {
        final CustomerEntry entry = this.service.addPoints(body.id(), body.points()); // Mise à jour de l'entrée avec les informations données
        return entry == null // Si l'entrée n'a pas été trouvée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été mise à jour
    }

    /**
     * Méthode permettant de mettre à jour une entrée de la collection Customer
     * @param body Les informations de l'entrée à mettre à jour
     * @return L'entrée mise à jour
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer updated"), // Réponse HTTP 200, l'entrée a été mise à jour
            @ApiResponse(responseCode = "404", description = "No customer found"), // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("edit") // Déclaration de la méthode edit comme étant une méthode HTTP POST
    public ResponseEntity<CustomerEntry> edit(
            @RequestBody CustomerEditBody body
    ) {
        final CustomerEntry entry = this.service.edit(body.id(), body.mail(), body.firstName(), body.lastName()); // Mise à jour de l'entrée avec les informations données
        return entry == null // Si l'entrée n'a pas été trouvée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été mise à jour
    }

    /**
     * Méthode permettant de supprimer une entrée de la collection Customer
     * @param id L'identifiant de l'entrée à supprimer
     * @return L'entrée supprimée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer deleted"), // Réponse HTTP 200, l'entrée a été supprimée
            @ApiResponse(responseCode = "404", description = "No customer found"), // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("delete") // Déclaration de la méthode delete comme étant une méthode HTTP POST
    public ResponseEntity<CustomerEntry> delete(
            @RequestBody int id
    ) {
        final CustomerEntry entry = this.service.delete(id); // Suppression de l'entrée correspondante à l'identifiant donné
        return entry == null // Si l'entrée n'a pas été trouvée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été supprimée
    }

}
