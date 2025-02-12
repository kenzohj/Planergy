/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories;

import fr.s401_equipe3b.server.entries.BonusEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface BonusRepository
 */
@Repository
public interface BonusRepository extends MongoRepository<BonusEntry, String> {

    /**
     * Trouve le bonus le plus proche de minAmount sans dépasser minAmount par ordre décroissant
     * @param minAmount Montant minimum
     * @return BonusEntry
     */
    Optional<BonusEntry> findFirstByMinAmountLessThanEqualOrderByMinAmountDesc(double minAmount);

    /**
     * Trouve le bonus le plus proche de minAmount sans dépasser minAmount par ordre croissant
     * @param minAmount Montant minimum
     * @return boolean
     */
    boolean existsByMinAmount(double minAmount);

    /**
     * Supprime le bonus le plus proche de minAmount sans dépasser minAmount
     * @param minAmount Montant minimum
     */
    void deleteByMinAmount(double minAmount);

}
