/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.entries;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * EnergyEntry
 */
@Document(collection = "energy")
public class EnergyEntry {

    private String name; // Nom de l'énergie
    private double price; // Prix de l'énergie
    private double quantity; // Quantité d'énergie
    private double limit; // Limite d'énergie

    public EnergyEntry() {} // Constructeur vide pour SpringBoot

    /**
     * Constructeur
     * @param name Nom de l'énergie
     * @param price Prix de l'énergie
     * @param quantity Quantité d'énergie
     * @param limit Limite d'énergie
     */
    public EnergyEntry(String name, double price, double quantity, double limit) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.limit = limit;
    }

    /**
     * Getter
     * @return Nom de l'énergie
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     * @return Prix de l'énergie
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter
     * @return Quantité d'énergie
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Getter
     * @return Limite d'énergie
     */
    public double getLimit() {
        return limit;
    }

}
