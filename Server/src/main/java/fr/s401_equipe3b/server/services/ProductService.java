/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.services;

import fr.s401_equipe3b.server.entries.ProductEntry;
import fr.s401_equipe3b.server.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Gestion des produits
 */
@Service
public class ProductService {

    // Repository des produits
    @Autowired
    private ProductRepository repository;

    /**
     * Récupérer les produits
     * @return Produits
     */
    public List<ProductEntry> getAll() {
        return this.repository.findAll();
    }

    /**
     * Récupérer un produit par son nom
     * @param name Nom du produit
     * @return Produit
     */
    public ProductEntry get(String name) {
        return this.repository.findByName(name);
    }

    /**
     * Créer un produit
     * @param name Nom du produit
     * @param price Prix du produit
     * @param weight Poids du produit
     * @param quantity Quantité du produit
     * @param limit Limite du produit
     * @return Produit
     */
    public ProductEntry create(String name, double price, double weight, int quantity, double limit) {
        final ProductEntry product = new ProductEntry(this.repository.getNextProductId(), name, price, weight, quantity, limit);
        return this.repository.save(product);
    }

    /**
     * Mettre à jour un produit
     * @param productId ID du produit
     * @param name Nom du produit
     * @param newPrice Nouveau prix du produit
     * @param newWeight Nouveau poids du produit
     * @param newQuantity Nouvelle quantité du produit
     * @param newLimit Nouvelle limite du produit
     * @return Produit
     */
    public ProductEntry update(int productId, String name, double newPrice, double newWeight, int newQuantity, double newLimit) {
        if(this.repository.findByProductId(productId) == null) return null;
        this.repository.deleteByProductId(productId);
        return this.repository.save(new ProductEntry(productId, name, newPrice, newWeight, newQuantity, newLimit));
    }

    /**
     * Supprimer un produit par son nom
     * @param name Nom du produit
     * @return Succès
     */
    public boolean delete(String name) {
        if(this.repository.findByName(name) == null) return false;
        this.repository.deleteByName(name);
        return true;
    }

    /**
     * Mettre à jour la quantité d'un produit
     * @param name Nom du produit
     * @param quantity Nouvelle quantité du produit
     */
    public void updateQuantity(String name, int quantity) {
        final ProductEntry entry = this.repository.findByName(name);
        if (entry == null) return;
        this.repository.deleteByName(name);
        this.repository.save(new ProductEntry(entry.getProductId(), entry.getName(), entry.getPrice(), entry.getWeight(), quantity, entry.getLimit()));
    }
    
}
