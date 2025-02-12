/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.controllers;

import fr.s401_equipe3b.server.body.EmployeeCreateBody;
import fr.s401_equipe3b.server.body.EmployeeUpdateBody;
import fr.s401_equipe3b.server.body.EmployeeVerifyBody;
import fr.s401_equipe3b.server.entries.EmployeeEntry;
import fr.s401_equipe3b.server.services.EmployeeService;
import fr.s401_equipe3b.server.utils.PasswordUtils;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Base64;
import java.util.List;

@RestController // Déclaration de la classe comme étant un contrôleur REST pour SpringBoot
@CrossOrigin(origins = "*") // Autorisation de requêtes HTTP depuis n'importe quelle origine
@RequestMapping("/api/employee") // Déclaration du chemin de base pour les requêtes HTTP relative à la collection Employee
public class EmployeeController {

    @Autowired // Injection de dépendance de la classe EmployeeService
    private EmployeeService service;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve all employees"), // Réponse HTTP 200, les entrées ont été trouvées
            @ApiResponse(responseCode = "404", description = "No data retrieved"), // Réponse HTTP 404, aucune entrée n'a été trouvée
    })
    @GetMapping("all") // Déclaration de la méthode getAll comme étant une méthode HTTP GET
    public ResponseEntity<List<EmployeeEntry>> getAll() {
        final List<EmployeeEntry> entries = this.service.getAll(); // Récupération de toutes les entrées de la collection Employee
        return entries.isEmpty() // Si la liste est vide...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entries); // Réponse HTTP 200, les entrées ont été trouvées
    }

    /**
     * Méthode permettant de récupérer une entrée de la collection Employee
     * @param mail L'adresse mail de l'entrée à récupérer
     * @return L'entrée correspondante à l'adresse mail donnée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve employee by mail"), // Réponse HTTP 200, l'entrée a été trouvée
            @ApiResponse(responseCode = "404", description = "Employee not found"), // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @GetMapping("entry") // Déclaration de la méthode get comme étant une méthode HTTP GET
    public ResponseEntity<EmployeeEntry> get(
            @RequestParam String mail
    ) {
        final EmployeeEntry entry = this.service.get(mail); // Récupération de l'entrée correspondante à l'adresse mail donnée
        return entry == null // Si l'entrée n'a pas été trouvée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été trouvée
    }

    /**
     * Méthode permettant de créer une nouvelle entrée dans la collection Employee
     * @param body Les informations de la nouvelle entrée à créer
     * @return La nouvelle entrée créée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "New employee created"), // Réponse HTTP 201, la nouvelle entrée a été créée
            @ApiResponse(responseCode = "409", description = "Employee already exists") // Réponse HTTP 409, une entrée correspondante existe déjà (adresse mail déjà utilisée
    })
    @PostMapping("create") // Déclaration de la méthode create comme étant une méthode HTTP POST
    public ResponseEntity<EmployeeEntry> create(
            @RequestBody EmployeeCreateBody body
    ) {
        final EmployeeEntry entry = this.service.create(body.mail(), body.password(), body.firstName(), body.lastName(), body.isAdmin()); // Création de la nouvelle entrée
        if(entry == null) return ResponseEntity.status(409).build(); // Si une entrée correspondante existe déjà, on renvoie une réponse HTTP 409 (Conflict
        return ResponseEntity.created(URI.create("/api/employee/entry?mail=" + entry.getMail())).body(entry); // Réponse HTTP 201, la nouvelle entrée a été créée
    }

    /**
     * Méthode permettant de valider une entrée de la collection Employee
     * @param mail L'adresse mail de l'entrée à valider
     * @return L'entrée validée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee validated"), // Réponse HTTP 200, l'entrée a été mise à jour
            @ApiResponse(responseCode = "404", description = "Employee not found"), // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("validate") // Déclaration de la méthode validate comme étant une méthode HTTP POST
    public ResponseEntity<EmployeeEntry> validate(
            @RequestBody String mail
    ) {
        final EmployeeEntry entry = this.service.validate(mail); // Validation de l'entrée correspondante à l'adresse mail donnée
        return entry == null // Si l'entrée n'a pas été trouvée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été mise à jour
    }

    /**
     * Méthode permettant de vérifier une entrée de la collection Employee
     * @param body Les informations de l'entrée à vérifier
     * @return L'entrée vérifiée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee verified"), // Réponse HTTP 200, l'entrée a été vérifiée
            @ApiResponse(responseCode = "203", description = "Employee not validated"), // Réponse HTTP 203, l'entrée n'a pas été validée
            @ApiResponse(responseCode = "404", description = "Employee not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("verify") // Déclaration de la méthode verify comme étant une méthode HTTP POST
    public ResponseEntity<EmployeeEntry> verify(
            @RequestBody EmployeeVerifyBody body
    ) {
        final EmployeeEntry entry = this.service.get(body.mail()); // Récupération de l'entrée correspondante à l'adresse mail donnée
        if(entry == null) return ResponseEntity.notFound().build(); // Si l'entrée n'a pas été trouvée, on renvoie une réponse HTTP 404
        if(!entry.isValidated()) return ResponseEntity.status(203).build(); // Si l'entrée n'a pas été validée, on renvoie une réponse HTTP 203
        if(!entry.getHash().equals(PasswordUtils.hashPassword(body.password(), Base64.getDecoder().decode(entry.getSalt())))) // Si le mot de passe donné ne correspond pas au mot de passe de l'entrée...
            return ResponseEntity.notFound().build(); // On renvoie une réponse HTTP 404
        return ResponseEntity.ok(entry); // Sinon, on renvoie une réponse HTTP 200
    }

    /**
     * Méthode permettant de mettre à jour une entrée de la collection Employee
     * @param body Les informations de l'entrée à mettre à jour
     * @return L'entrée mise à jour
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated"), // Réponse HTTP 200, l'entrée a été mise à jour
            @ApiResponse(responseCode = "404", description = "Employee not found"), // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("update") // Déclaration de la méthode update comme étant une méthode HTTP POST
    public ResponseEntity<EmployeeEntry> update(
            @RequestBody EmployeeUpdateBody body
    ) {
        final EmployeeEntry entry = this.service.update(body.oldMail(), body.mail(), body.password(), body.firstName(), body.lastName(), body.isAdmin()); // Mise à jour de l'entrée avec les informations données
        return entry == null // Si l'entrée n'a pas été trouvée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été mise à jour
    }

    /**
     * Méthode permettant de supprimer une entrée de la collection Employee
     * @param mail L'adresse mail de l'entrée à supprimer
     * @return Un booléen indiquant si l'entrée a été supprimée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee deleted"), // Réponse HTTP 200, l'entrée a été supprimée
            @ApiResponse(responseCode = "404", description = "Employee not found"), // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("delete") // Déclaration de la méthode delete comme étant une méthode HTTP POST
    public ResponseEntity<Boolean> delete(
            @RequestBody String mail
    ) {
        return this.service.delete(mail) // Suppression de l'entrée correspondante à l'adresse mail donnée
                ? ResponseEntity.ok(true) // Si l'entrée a été supprimée, on renvoie une réponse HTTP 200
                : ResponseEntity.notFound().build(); // Sinon, on renvoie une réponse HTTP 404
    }

}
