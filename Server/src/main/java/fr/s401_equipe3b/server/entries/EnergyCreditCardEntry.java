/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.entries;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * EnergyCreditCardEntry
 */
@Document(collection = "energycreditcard")
public class EnergyCreditCardEntry {

    private int numbers; // Numéro de la carte
    private double amount; // Montant de la carte
    private String date; // Date d'expiration
    private int cvv; // Code de sécurité
    private List<EnergyCreditCardTransactionEntry> transactions; // Transactions

    public EnergyCreditCardEntry() {} // Constructeur vide pour SpringBoot

    /**
     * Constructeur
     * @param numbers Numéro de la carte
     * @param amount Montant de la carte
     * @param date Date d'expiration
     * @param cvv Code de sécurité
     * @param transactions Transactions
     */
    public EnergyCreditCardEntry(int numbers, double amount, String date, int cvv, List<EnergyCreditCardTransactionEntry> transactions) {
        this.numbers = numbers;
        this.amount = amount;
        this.date = date;
        this.cvv = cvv;
        this.transactions = transactions;
    }

    /**
     * Getter
     * @return Montant de la carte
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Getter
     * @return Numéro de la carte
     */
    public int getNumbers() {
        return this.numbers;
    }

    /**
     * Getter
     * @return Date d'expiration
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Getter
     * @return Code de sécurité
     */
    public int getCVV() {
        return this.cvv;
    }

    /**
     * Getter
     * @return Transactions
     */
    public List<EnergyCreditCardTransactionEntry> getTransactions() {
        return this.transactions;
    }

}
