/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.entries;

import fr.s401_equipe3b.server.utils.PaymentMethod;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * CashRegisterTransactionEntry
 */
@Document(collection = "cashRegisterTransaction") // Nom de la collection dans la base de données
public class CashRegisterTransactionEntry {

    private int transactionId; // Identifiant de la transaction
    private String mail; // Mail de l'employé
    private int customerId; // Identifiant du client
    private String date; // Date de la transaction
    private double amount; // Montant de la transaction
    private Map<PaymentMethod, Double> payment; // Méthode(s) de paiement et montant(s)
    private Map<String, Integer> products; // Produit(s) et quantité(s)
    private Map<String, Integer> energies; // Énergie(s) et quantité(s)
    private boolean validated; // Validation de la transaction

    public CashRegisterTransactionEntry() {} // Constructeur vide pour SpringBoot

    /**
     * Constructeur
     * @param transactionId Identifiant de la transaction
     * @param mail Mail de l'employé
     * @param customerId Identifiant du client
     * @param date Date de la transaction
     * @param amount Montant de la transaction
     * @param payment Méthode(s) de paiement et montant(s)
     * @param products Produit(s) et quantité(s)
     * @param energies Énergie(s) et quantité(s)
     * @param validated Validation de la transaction
     */
    public CashRegisterTransactionEntry(int transactionId, String mail, int customerId, String date, double amount, Map<PaymentMethod, Double> payment, Map<String, Integer> products, Map<String, Integer> energies, boolean validated) {
        this.transactionId = transactionId;
        this.mail = mail;
        this.customerId = customerId;
        this.date = date;
        this.amount = amount;
        this.payment = payment;
        this.products = products;
        this.energies = energies;
        this.validated = validated;
    }

    /**
     * Getter
     * @return Identifiant de la transaction
     */
    public int getTransactionId() {
        return this.transactionId;
    }

    /**
     * Getter
     * @return Mail de l'employé
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * Getter
     * @return Identifiant du client
     */
    public int getCustomerId() {
        return this.customerId;
    }

    /**
     * Getter
     * @return Date de la transaction
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Getter
     * @return Montant de la transaction
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Getter
     * @return Méthode(s) de paiement et montant(s)
     */
    public Map<PaymentMethod, Double> getPayment() {
        return this.payment;
    }

    /**
     * Getter
     * @return Produit(s) et quantité(s)
     */
    public Map<String, Integer> getProducts() {
        return this.products;
    }

    /**
     * Getter
     * @return Énergie(s) et quantité(s)
     */
    public Map<String, Integer> getEnergies() {
        return this.energies;
    }

    /**
     * Getter
     * @return Validation de la transaction
     */
    public boolean isValidated() {
        return this.validated;
    }

}
