/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.controllers;

import fr.s401_equipe3b.server.body.CashRegisterTransactionCreateBody;
import fr.s401_equipe3b.server.entries.CashRegisterTransactionEntry;
import fr.s401_equipe3b.server.services.CashRegisterTransactionService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController // Déclaration de la classe comme étant un contrôleur REST pour SpringBoot
@CrossOrigin(origins = "*") // Autorisation de requêtes HTTP depuis n'importe quelle origine
@RequestMapping("/api/crt") // Déclaration du chemin de base pour les requêtes HTTP relative à la collection CashRegisterTransaction
public class CashRegisterTransactionController {

    @Autowired // Injection de dépendance de la classe CashRegisterTransactionService
    private CashRegisterTransactionService service;

    /**
     * Méthode permettant de récupérer une entrée de la collection CashRegisterTransaction
     * @param id L'identifiant de l'entrée à récupérer
     * @return L'entrée correspondante à l'identifiant donné
     */
    @ApiResponses({ // Déclaration des réponses possibles pour la méthode getEntry
            @ApiResponse(responseCode = "200", description = "Entry found"), // Réponse HTTP 200, l'entrée a été trouvée
            @ApiResponse(responseCode = "404", description = "Not entry corresponding found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @GetMapping("/entry") // Déclaration de la méthode getEntry comme étant une méthode HTTP GET
    public ResponseEntity<CashRegisterTransactionEntry> getEntry(
            @RequestParam(value = "id") int id
    ) {
        final CashRegisterTransactionEntry entry = this.service.get(id); // Récupération de l'entrée correspondante à l'identifiant donné
        return entry == null // Vérification si l'entrée est nulle
                ? ResponseEntity.notFound().build() // Si l'entrée est nulle, on renvoie une réponse HTTP 404
                : ResponseEntity.ok(entry); // Sinon, on renvoie une réponse HTTP 200 avec l'entrée
    }

    /**
     * Méthode permettant de récupérer toutes les entrées de la collection CashRegisterTransaction
     * @return La liste des entrées récupérées
     */
    @ApiResponses({ // Déclaration des réponses possibles pour la méthode getAll
            @ApiResponse(responseCode = "200", description = "Retrieve all cash register transactions"), // Réponse HTTP 200, les entrées ont été récupérées
            @ApiResponse(responseCode = "404", description = "No data retrieved") // Réponse HTTP 404, aucune entrée n'a été récupérée
    })
    @GetMapping("/all") // Déclaration de la méthode getAll comme étant une méthode HTTP GET
    public ResponseEntity<List<CashRegisterTransactionEntry>> getAll() {
        return this.service.getAll().isEmpty() // Vérification si la liste des entrées est vide
                ? ResponseEntity.notFound().build() // Si la liste est vide, on renvoie une réponse HTTP 404
                : ResponseEntity.ok(this.service.getAll()); // Sinon, on renvoie une réponse HTTP 200 avec la liste des entrées
    }

    /**
     * Méthode permettant de créer une nouvelle entrée dans la collection CashRegisterTransaction
     * @param body Les informations nécessaires à la création de l'entrée
     * @return L'entrée créée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "New cash register transaction entry created") // Réponse HTTP 201, l'entrée a été créée
    })
    @PostMapping("/create") // Déclaration de la méthode create comme étant une méthode HTTP POST
    public ResponseEntity<CashRegisterTransactionEntry> create(
            @RequestBody CashRegisterTransactionCreateBody body
    ) {
        final CashRegisterTransactionEntry entry = this.service.create(body.mail(), body.customerId(), body.date(), body.amount(), body.payment(), body.products(), body.energies()); // Création de l'entrée
        return ResponseEntity.created(URI.create("api/crt/entry?id=" + entry.getTransactionId())).body(entry); // Renvoi de la réponse HTTP 201
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cash register transaction entry validated"), // Réponse HTTP 200, l'entrée a été validée
            @ApiResponse(responseCode = "404", description = "No entry corresponding found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("/validate") // Déclaration de la méthode validate comme étant une méthode HTTP POST
    public ResponseEntity<CashRegisterTransactionEntry> validate(
            @RequestBody int transactionId
    ) {
        final CashRegisterTransactionEntry entry = this.service.validate(transactionId); // Validation de l'entrée
        return entry == null
                ? ResponseEntity.notFound().build() // Si l'entrée est nulle, on renvoie une réponse HTTP 404
                : ResponseEntity.ok(this.service.get(transactionId)); // Sinon, on renvoie une réponse HTTP 200 avec l'entrée
    }

}
