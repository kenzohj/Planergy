/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories;

import fr.s401_equipe3b.server.entries.CashRegisterTransactionEntry;
import fr.s401_equipe3b.server.repositories.custom.CustomCRTRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface CashRegisterTransactionRepository
 */
@Repository
public interface CashRegisterTransactionRepository extends MongoRepository<CashRegisterTransactionEntry, String>, CustomCRTRepository {

    /**
     * Trouve l'entrée de transaction par l'identifiant de transaction
     * @param transactionId Identifiant de transaction
     * @return CashRegisterTransactionEntry
     */
    CashRegisterTransactionEntry findByTransactionId(int transactionId);

    /**
     * Supprime l'entrée de transaction par l'identifiant de transaction
     * @param transactionId Identifiant de transaction
     */
    void deleteByTransactionId(int transactionId);

}
