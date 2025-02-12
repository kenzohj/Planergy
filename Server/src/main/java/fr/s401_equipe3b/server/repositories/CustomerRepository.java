/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories;

import fr.s401_equipe3b.server.entries.CustomerEntry;
import fr.s401_equipe3b.server.repositories.custom.CustomCustomerRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface CustomerRepository
 */
@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntry, String>, CustomCustomerRepository {

    /**
     * Trouve l'entrée de client par l'identifiant de client
     * @param id Identifiant de client
     * @return CustomerEntry
     */
    CustomerEntry findByCustomerId(int id);

    /**
     * Supprime l'entrée de client par l'identifiant de client
     * @param id Identifiant de client
     */
    void deleteByCustomerId(int id);

    /**
     * Trouve l'entrée de client par le mail de client
     * @param mail Mail de client
     * @return CustomerEntry
     */
    CustomerEntry findByMail(String mail);

}
