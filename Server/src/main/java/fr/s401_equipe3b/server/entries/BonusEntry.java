/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.entries;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * BonusEntry
 */
@Document(collection = "bonus")
public class BonusEntry {

    private double minAmount; // Montant minimum pour obtenir le bonus
    private double bonusAmount; // Montant du bonus

    public BonusEntry() {} // Constructeur vide pour SpringBoot

    /**
     * Constructeur
     * @param minAmount Montant minimum pour obtenir le bonus
     * @param bonusAmount Montant du bonus
     */
    public BonusEntry(int minAmount, int bonusAmount) {
        this.minAmount = minAmount;
        this.bonusAmount = bonusAmount;
    }

    /**
     * Getter
     * @return Montant minimum pour obtenir le bonus
     */
    public double getMinAmount() {
        return this.minAmount;
    }

    /**
     * Getter
     * @return Montant du bonus
     */
    public double getBonusAmount() {
        return this.bonusAmount;
    }

}
