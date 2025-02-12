/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.controllers;

import fr.s401_equipe3b.server.body.EnergyCreateBody;
import fr.s401_equipe3b.server.body.EnergyUpdateBody;
import fr.s401_equipe3b.server.entries.EnergyEntry;
import fr.s401_equipe3b.server.services.EnergyService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController // Déclaration de la classe comme étant un contrôleur REST pour SpringBoot
@CrossOrigin(origins = "*") // Autorisation de requêtes HTTP depuis n'importe quelle origine
@RequestMapping("/api/energy") // Déclaration du chemin de base pour les requêtes HTTP relative à la collection Energy
public class EnergyController {

    @Autowired // Injection de dépendance de la classe EnergyService
    private EnergyService service;

    /**
     * Méthode permettant de récupérer toutes les entrées de la collection Energy
     * @return La liste des entrées récupérées
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get all energies"), // Réponse HTTP 200, les entrées ont été trouvées
            @ApiResponse(responseCode = "404", description = "No energy found") // Réponse HTTP 404, aucune entrée n'a été trouvée
    })
    @GetMapping("/all") // Déclaration de la méthode getAllEnergies comme étant une méthode HTTP GET
    public ResponseEntity<List<EnergyEntry>> getAllEnergies() {
        return this.service.getAll().isEmpty() // Si la liste est vide...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(this.service.getAll()); // Réponse HTTP 200, les entrées ont été trouvées
    }

    /**
     * Méthode permettant de récupérer une entrée de la collection Energy
     * @param name Le nom de l'entrée à récupérer
     * @return L'entrée correspondante au nom donné
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Energy found"), // Réponse HTTP 200, l'entrée a été trouvée
            @ApiResponse(responseCode = "404", description = "Energy not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @GetMapping("/entry") // Déclaration de la méthode getEnergy comme étant une méthode HTTP GET
    public ResponseEntity<EnergyEntry> getEnergy(
            @RequestParam String name
    ) {
        final EnergyEntry entry = this.service.get(name.toLowerCase()); // Récupération de l'entrée correspondante au nom donné
        return entry == null // Si l'entrée n'a pas été trouvée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(entry); // Réponse HTTP 200, l'entrée a été trouvée
    }

    /**
     * Méthode permettant de mettre à jour une entrée de la collection Energy
     * @param body Les informations de l'entrée à mettre à jour
     * @return L'entrée mise à jour
     */
    @PostMapping("/update")
    public ResponseEntity<EnergyEntry> update(
            @RequestBody EnergyUpdateBody body
    ) {
        final EnergyEntry energy = this.service.update(body.name(), body.price(), body.quantity(), body.limit()); // Mise à jour de l'entrée
        return energy == null // Si l'entrée n'a pas été mise à jour...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(energy); // Réponse HTTP 200, l'entrée a été mise à jour
    }

    /**
     * Méthode permettant de créer une nouvelle entrée dans la collection Energy
     * @param body Les informations de la nouvelle entrée à créer
     * @return La nouvelle entrée créée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "New energy entry created"), // Réponse HTTP 201, la nouvelle entrée a été créée
            @ApiResponse(responseCode = "409", description = "An energy with this name already exists") // Réponse HTTP 409, une entrée avec ce nom existe déjà
    })
    @PostMapping("/create")
    public ResponseEntity<EnergyEntry> createEnergy(
            @RequestBody EnergyCreateBody body
    ) {
        final EnergyEntry entry = this.service.create(body.name().toLowerCase(), body.price(), body.quantity(), body.limit()); // Création de la nouvelle entrée
        return entry == null // Si l'entrée n'a pas été créée...
                ? ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.CONFLICT.value())).build() // Réponse HTTP 409, une entrée avec ce nom existe déjà
                : ResponseEntity.created(URI.create("/api/energy/entry?name=" + entry.getName())).body(entry); // Réponse HTTP 201, la nouvelle entrée a été créée
    }

    /**
     * Méthode permettant de supprimer une entrée de la collection Energy
     * @param name Le nom de l'entrée à supprimer
     * @return Un booléen indiquant si l'entrée a été supprimée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Energy found"), // Réponse HTTP 200, l'entrée a été supprimée
            @ApiResponse(responseCode = "404", description = "Energy not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteEnergy(
            @RequestBody String name
    ) {
        final boolean entry = this.service.delete(name.toLowerCase()); // Suppression de l'entrée correspondante au nom donné
        return !entry // Si l'entrée n'a pas été supprimée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(true); // Réponse HTTP 200, l'entrée a été supprimée
    }

}
