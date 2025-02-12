/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.services;

import fr.s401_equipe3b.server.entries.BonusEntry;
import fr.s401_equipe3b.server.repositories.BonusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Gestion des bonus
 */
@Service
public class BonusService {

    // Repository des bonus
    @Autowired
    private BonusRepository repository;

    /**
     * Récupérer le bonus associé à un montant
     * @param amount Montant
     * @return Bonus associé
     */
    public Optional<BonusEntry> get(int amount) {
        return this.repository.findFirstByMinAmountLessThanEqualOrderByMinAmountDesc(amount);
    }

    /**
     * Supprimer un bonus
     * @param amount Montant
     * @return Réussite
     */
    public boolean delete(int amount) {
        if(!this.repository.existsByMinAmount(amount)) return false;
        this.repository.deleteByMinAmount(amount);
        return true;
    }

    /**
     * Créer un bonus
     * @param amount Montant
     * @param bonus Bonus
     * @return Bonus créé
     */
    public Optional<BonusEntry> create(int amount, int bonus) {
        if(this.repository.existsByMinAmount(amount)) return Optional.empty();
        return Optional.of(this.repository.save(new BonusEntry(amount, bonus)));
    }

    /**
     * Modifier un bonus
     * @param amount Montant
     * @param newAmount Nouveau montant
     * @param newBonus Nouveau bonus
     * @return Réussite
     */
    public boolean edit(int amount, int newAmount, int newBonus) {
        if(!this.repository.existsByMinAmount(amount)) return false;
        this.repository.deleteByMinAmount(amount);
        this.repository.save(new BonusEntry(newAmount, newBonus));
        return true;
    }

    public List<BonusEntry> getAll() {
        return this.repository.findAll();
    }

}
