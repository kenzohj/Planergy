/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.controllers;

import fr.s401_equipe3b.server.body.DocumentCreateBody;
import fr.s401_equipe3b.server.entries.DocumentEntry;
import fr.s401_equipe3b.server.services.DocumentService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController // Déclaration de la classe comme étant un contrôleur REST pour SpringBoot
@CrossOrigin(origins = "*") // Autorisation de requêtes HTTP depuis n'importe quelle origine
@RequestMapping("/api/document") // Déclaration du chemin de base pour les requêtes HTTP relative à la collection Document
public class DocumentController {

    @Autowired // Injection de dépendance de la classe DocumentService
    private DocumentService service;

    /**
     * Méthode permettant de récupérer toutes les entrées de la collection Document
     * @return La liste des entrées récupérées
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Documents found"), // Réponse HTTP 200, les entrées ont été trouvées
            @ApiResponse(responseCode = "404", description = "No documents found") // Réponse HTTP 404, aucune entrée n'a été trouvée
    })
    @GetMapping("/all") // Déclaration de la méthode getAll comme étant une méthode HTTP GET
    public ResponseEntity<List<DocumentEntry>> getAll() {
        final List<DocumentEntry> documents = this.service.getAll(); // Récupération de toutes les entrées de la collection Document
        return documents.isEmpty() // Si la liste est vide...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(documents); // Réponse HTTP 200, les entrées ont été trouvées
    }

    /**
     * Méthode permettant de récupérer une entrée de la collection Document
     * @param id L'identifiant de l'entrée à récupérer
     * @return L'entrée correspondante à l'identifiant donné
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Document found"), // Réponse HTTP 200, l'entrée a été trouvée
            @ApiResponse(responseCode = "404", description = "Document not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @GetMapping("/entry") // Déclaration de la méthode getEntry comme étant une méthode HTTP GET
    public ResponseEntity<DocumentEntry> getEntry(
            @RequestParam int id
    ) {
        final DocumentEntry document = this.service.get(id); // Récupération de l'entrée correspondante à l'identifiant donné
        return document != null // Si l'entrée a été trouvée...
                ? ResponseEntity.ok(document) // Si l'entrée a été trouvée, réponse HTTP 200
                : ResponseEntity.notFound().build(); // Sinon, réponse HTTP 404
    }

    /**
     * Méthode permettant de créer une nouvelle entrée dans la collection Document
     * @param body Les informations de la nouvelle entrée à créer
     * @return La nouvelle entrée créée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "New Document created"), // Réponse HTTP 201, la nouvelle entrée a été créée
    })
    @PostMapping("/create") // Déclaration de la méthode createDocument comme étant une méthode HTTP POST
    public ResponseEntity<DocumentEntry> createDocument(
            @RequestBody DocumentCreateBody body
    ) {
        final DocumentEntry createdDocument = this.service.create(body.name(), body.restricted(), body.date(), body.author(), body.content()); // Création de la nouvelle entrée
        return ResponseEntity.created(URI.create("/api/document/entry?id=" + createdDocument.getDocumentId())).body(createdDocument); // Réponse HTTP 201, la nouvelle entrée a été créée
    }

}
