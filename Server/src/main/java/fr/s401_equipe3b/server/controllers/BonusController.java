/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.controllers;

import fr.s401_equipe3b.server.body.BonusCreateBody;
import fr.s401_equipe3b.server.body.BonusEditBody;
import fr.s401_equipe3b.server.entries.BonusEntry;
import fr.s401_equipe3b.server.services.BonusService;
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
@RequestMapping("/api/bonus") // Déclaration du chemin de base pour les requêtes HTTP relative à la collection Bonus
public class BonusController {

    @Autowired // Injection de dépendance de la classe BonusService
    private BonusService service;

    /**
     * Méthode permettant de récupérer une entrée de la collection Bonus
     * @param minAmount Le montant minimum pour lequel récupérer le bonus
     * @return L'entrée correspondante au montant minimum donné
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bonus found"),
            @ApiResponse(responseCode = "404", description = "No bonus found")
    })
    @GetMapping("/bonus")
    public ResponseEntity<BonusEntry> getBonus(
            @RequestParam int minAmount
    ) {
        return this.service.get(minAmount) // Récupération de l'entrée correspondante au montant minimum donné
                .map(ResponseEntity::ok) // Si l'entrée a été trouvée, on renvoie une réponse HTTP 200
                .orElseGet(() -> ResponseEntity.notFound().build()); // Sinon, on renvoie une réponse HTTP 404
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieve all Bonuses"),
            @ApiResponse(responseCode = "404", description = "No data retrieved")
    })
    @GetMapping("/all")
    public ResponseEntity<List<BonusEntry>> getAll() {
        final List<BonusEntry> entries = this.service.getAll(); // Récupération de toutes les entrées de la collection
        System.out.println(entries);
        return !entries.isEmpty() // Si la liste n'est pas vide...
                ? ResponseEntity.ok(entries) // Réponse HTTP 200, les entrées ont été trouvées
                : ResponseEntity.notFound().build(); // Réponse HTTP 404, aucune entrée n'a été trouvée
    }


    /**
     * Méthode permettant de supprimer une entrée de la collection Bonus
     * @param minAmount Le montant minimum pour lequel supprimer le bonus
     * @return True si l'entrée a été supprimée, false sinon
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bonus deleted"),
            @ApiResponse(responseCode = "404", description = "Bonus not found")
    })
    @PostMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestBody int minAmount
    ) {
        return this.service.delete(minAmount)
                ? ResponseEntity.ok(true) // Si l'entrée a été supprimée, on renvoie une réponse HTTP 200
                : ResponseEntity.notFound().build(); // Sinon, on renvoie une réponse HTTP 404
    }

    /**
     * Méthode permettant de créer une nouvelle entrée dans la collection Bonus
     * @param body Les informations pour créer la nouvelle entrée
     * @return L'entrée créée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "New bonus entry created"), // Réponse HTTP 201, la nouvelle entrée a été créée
            @ApiResponse(responseCode = "409", description = "Bonus at this amount already exist") // Réponse HTTP 409, une entrée existe déjà pour le montant minimum donné
    })
    @PostMapping("/create") // Déclaration de la méthode create comme étant une méthode HTTP POST
    public ResponseEntity<BonusEntry> create(
            @RequestBody BonusCreateBody body
            ) {
        return this.service.create(body.minAmount(), body.bonus()) // Création de la nouvelle entrée
                .map(bonusEntry -> ResponseEntity.created(URI.create("api/bonus/bonus?minAmount=" + body.minAmount())).body(bonusEntry)) // Si la nouvelle entrée a été créée, on renvoie une réponse HTTP 201
                .orElseGet(() -> ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.CONFLICT.value())).build()); // Sinon, on renvoie une réponse HTTP 409
    }

    /**
     * Méthode permettant de modifier une entrée de la collection Bonus
     * @param body Les informations pour modifier l'entrée
     * @return True si l'entrée a été modifiée, false sinon
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bonus modified"),
            @ApiResponse(responseCode = "404", description = "Bonus not found")
    })
    @PostMapping("/edit") // Déclaration de la méthode edit comme étant une méthode HTTP POST
    public ResponseEntity<Boolean> edit(
            @RequestBody BonusEditBody body
            ) {
        return this.service.edit(body.minAmount(), body.newMinAmount(), body.newBonusAmount()) // Modification de l'entrée
                ? ResponseEntity.ok(true) // Si l'entrée a été modifiée, on renvoie une réponse HTTP 200
                : ResponseEntity.notFound().build();  // Sinon, on renvoie une réponse HTTP 404
    }

}
