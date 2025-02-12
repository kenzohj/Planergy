/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.entries;

import fr.s401_equipe3b.server.utils.RestockStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * RestockEntry
 */
@Document(collection = "restock")
public class RestockEntry {

    private int restockId; // ID de la commande
    private String userId; // ID de l'utilisateur
    private RestockStatus status; // Statut de la commande
    private String date; // Date de la commande
    private String arrivalDate; // Date d'arrivée de la commande
    private Map<String, Integer> order; // Produits et énergies commandés
    private double amount; // Montant total de la commande

    public RestockEntry() {} // Constructeur vide pour SpringBoot

    /**
     * Constructeur
     * @param restockId ID de la commande
     * @param userId ID de l'utilisateur
     * @param status Statut de la commande
     * @param date Date de la commande
     * @param arrivalDate Date d'arrivée de la commande
     * @param order Produits et énergies commandés
     * @param amount Montant total de la commande
     */
    public RestockEntry(int restockId, String userId, RestockStatus status, String date, String arrivalDate, Map<String, Integer> order, double amount) {
        this.restockId = restockId;
        this.userId = userId;
        this.status = status;
        this.date = date;
        this.arrivalDate = arrivalDate;
        this.order = order;
        this.amount = amount;
    }

    /**
     * Getter
     * @return ID de la commande
     */
    public int getRestockId() {
        return this.restockId;
    }

    /**
     * Getter
     * @return ID de l'utilisateur
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Getter
     * @return Statut de la commande
     */
    public RestockStatus getStatus() {
        return this.status;
    }

    /**
     * Getter
     * @return Date de la commande
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter
     * @return Date d'arrivée de la commande
     */
    public String getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Getter
     * @return Produits et énergies commandés
     */
    public Map<String, Integer> getOrder() {
        return this.order;
    }

    /**
     * Getter
     * @return Montant total de la commande
     */
    public double getAmount() {
        return this.amount;
    }

}
