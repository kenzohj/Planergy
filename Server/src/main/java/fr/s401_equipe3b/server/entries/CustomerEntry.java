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
 * CustomerEntry
 */
@Document(collection = "customer")
public class CustomerEntry {

    private int customerId; // Identifiant du client
    private String mail; // Adresse mail du client
    private String firstName; // Prénom du client
    private String lastName; // Nom du client
    private int points; // Points du client
    private EnergyCreditCardEntry eCard; // Carte d'énergie du client
    private List<EnergyCreditCardTransactionEntry> eCardTransactions; // Transactions de la carte d'énergie du client

    public CustomerEntry() {} // Constructeur vide pour SpringBoot

    /** Constructeur
     * @param customerId Identifiant du client
     * @param mail Adresse mail du client
     * @param firstName Prénom du client
     * @param lastName Nom du client
     * @param points Points du client
     * @param eCard Carte d'énergie du client
     * @param eCardTransactions Transactions de la carte d'énergie du client
     */
    public CustomerEntry(int customerId, String mail, String firstName, String lastName, int points, EnergyCreditCardEntry eCard, List<EnergyCreditCardTransactionEntry> eCardTransactions) {
        this.customerId = customerId;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
        this.eCard = eCard;
        this.eCardTransactions = eCardTransactions;
    }

    /** Getter
     * @return Identifiant du client
     */
    public int getCustomerId() {
        return this.customerId;
    }

    /** Getter
     * @return Adresse mail du client
     */
    public String getMail() {
        return this.mail;
    }

    /** Getter
     * @return Prénom du client
     */
    public String getFirstName() {
        return this.firstName;
    }

    /** Getter
     * @return Nom du client
     */
    public String getLastName() {
        return this.lastName;
    }

    /** Getter
     * @return Points du client
     */
    public int getPoints() {
        return this.points;
    }

    /** Getter
     * @return Carte d'énergie du client
     */
    public EnergyCreditCardEntry getEnergyCreditCard() {
        return this.eCard;
    }

    /** Getter
     * @return Transactions de la carte d'énergie du client
     */
    public List<EnergyCreditCardTransactionEntry> getEnergyCreditCardTransactions() {
        return this.eCardTransactions;
    }

}
