/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.entries;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * EnergyCreditCardTransactionEntry
 */
@Document(collection = "energycreditcardtransaction")
public class EnergyCreditCardTransactionEntry {

    private int transactionId; // Identifiant de la transaction
    private double amount; // Montant de la transaction
    private String date; // Date de la transaction

    public EnergyCreditCardTransactionEntry() {} // Constructeur vide pour SpringBoot

    /**
     * Constructeur
     * @param transactionId Identifiant de la transaction
     * @param amount Montant de la transaction
     * @param date Date de la transaction
     */
    public EnergyCreditCardTransactionEntry(int transactionId, double amount, String date) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
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
     * @return Montant de la transaction
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Getter
     * @return Date de la transaction
     */
    public String getDate() {
        return this.date;
    }

}
