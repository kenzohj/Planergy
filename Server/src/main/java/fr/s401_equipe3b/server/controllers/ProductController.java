/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.controllers;

import fr.s401_equipe3b.server.body.ProductCreateBody;
import fr.s401_equipe3b.server.body.ProductUpdateBody;
import fr.s401_equipe3b.server.entries.ProductEntry;
import fr.s401_equipe3b.server.services.ProductService;
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
@RequestMapping("/api/product") // Déclaration du chemin de base pour les requêtes HTTP relative à la collection Product
public class ProductController {

    @Autowired // Injection de dépendance de la classe ProductService
    private ProductService service;

    /**
     * Méthode permettant de récupérer toutes les entrées de la collection Product
     * @return La liste des entrées récupérées
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get all products"), // Réponse HTTP 200, les entrées ont été trouvées
            @ApiResponse(responseCode = "404", description = "No product found") // Réponse HTTP 404, aucune entrée n'a été trouvée
    })
    @GetMapping("/all") // Déclaration de la méthode getAll comme étant une méthode HTTP GET
    public ResponseEntity<List<ProductEntry>> getAll() {
        return this.service.getAll().isEmpty() // Si la liste est vide...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(this.service.getAll()); // Réponse HTTP 200, les entrées ont été trouvées
    }

    /**
     * Méthode permettant de récupérer une entrée de la collection Product
     * @param name Le nom de l'entrée à récupérer
     * @return L'entrée correspondante au nom donné
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found"), // Réponse HTTP 200, l'entrée a été trouvée
            @ApiResponse(responseCode = "404", description = "Product not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @GetMapping("/entry") // Déclaration de la méthode getEntry comme étant une méthode HTTP GET
    public ResponseEntity<ProductEntry> getEntry(
            @RequestParam String name
    ) {
        final ProductEntry product = this.service.get(name.toLowerCase()); // Récupération de l'entrée correspondante au nom donné
        return product == null // Si l'entrée n'a pas été trouvée...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(product); // Réponse HTTP 200, l'entrée a été trouvée
    }

    /**
     * Méthode permettant de créer une nouvelle entrée dans la collection Product
     * @param body Les informations de la nouvelle entrée à créer
     * @return La nouvelle entrée créée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "New product entry created"), // Réponse HTTP 201, la nouvelle entrée a été créée
            @ApiResponse(responseCode = "409", description = "A product with this name already exists") // Réponse HTTP 409, une entrée avec ce nom existe déjà
    })
    @PostMapping("/create")
    public ResponseEntity<ProductEntry> create(
            @RequestBody ProductCreateBody body
    ) {
        final ProductEntry product = this.service.create(body.name().toLowerCase(), body.price(), body.weight(), body.quantity(), body.limit()); // Création de la nouvelle entrée
        return product == null // Si la nouvelle entrée n'a pas pu être créée...
                ? ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.CONFLICT.value())).build() // Réponse HTTP 409, une entrée avec ce nom existe déjà
                : ResponseEntity.created(URI.create("/api/product/entry?name=" + body.name().toLowerCase().replace(" ", "+"))).body(product); // Réponse HTTP 201, la nouvelle entrée a été créée
    }

    /**
     * Méthode permettant de mettre à jour une entrée de la collection Product
     * @param body Les informations de l'entrée à mettre à jour
     * @return L'entrée mise à jour
     */
    @PostMapping("/update")
    public ResponseEntity<ProductEntry> update(
            @RequestBody ProductUpdateBody body
    ) {
        final ProductEntry product = this.service.update(body.productId(), body.name(), body.newPrice(), body.newWeight(), body.newQuantity(), body.newLimit()); // Mise à jour de l'entrée
        return product == null // Si l'entrée n'a pas été mise à jour...
                ? ResponseEntity.notFound().build() // Réponse HTTP 404, aucune entrée n'a été trouvée
                : ResponseEntity.ok(product); // Réponse HTTP 200, l'entrée a été mise à jour
    }

    /**
     * Méthode permettant de supprimer une entrée de la collection Product
     * @param name Le nom de l'entrée à supprimer
     * @return Un booléen indiquant si l'entrée a été supprimée
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product deleted"), // Réponse HTTP 200, l'entrée a été supprimée
            @ApiResponse(responseCode = "404", description = "Product not found") // Réponse HTTP 404, aucune entrée correspondante n'a été trouvée
    })
    @PostMapping("/delete") // Déclaration de la méthode delete comme étant une méthode HTTP POST
    public ResponseEntity<Boolean> delete(
            @RequestBody String name
    ) {
        return this.service.delete(name) // Suppression de l'entrée correspondante au nom donné
                ? ResponseEntity.ok(true) // Si l'entrée a été supprimée, on renvoie une réponse HTTP 200
                : ResponseEntity.notFound().build(); // Sinon, on renvoie une réponse HTTP 404
    }

}
