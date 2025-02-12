/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.entries;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ProductEntry
 */
@Document(collection = "product")
public class ProductEntry {

    private int productId; // Identifiant du produit
    private String name; // Nom du produit
    private double price; // Prix du produit (au kilo)
    private double weight; // Poids du produit
    private int quantity; // Quantité du produit
    private double limit; // Seuil d'alerte

    public ProductEntry() {} // Constructeur vide pour SpringBoot

    /**
     * Constructeur
     * @param productId Identifiant du produit
     * @param name Nom du produit
     * @param price Prix du produit (au kilo)
     * @param weight Poids du produit
     * @param quantity Quantité du produit
     * @param limit Seuil d'alerte
     */
    public ProductEntry(int productId, String name, double price, double weight, int quantity, double limit) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.limit = limit;
    }

    /**
     * Getter
     * @return Identifiant du produit
     */
    public int getProductId() {
        return this.productId;
    }

    /**
     * Getter
     * @return Nom du produit
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter
     * @return Prix du produit (au kilo)
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Getter
     * @return Poids du produit
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Getter
     * @return Quantité du produit
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Getter
     * @return Seuil d'alerte
     */
    public double getLimit() {
        return this.limit;
    }

}
