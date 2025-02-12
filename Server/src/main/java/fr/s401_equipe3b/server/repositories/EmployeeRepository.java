/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories;

import fr.s401_equipe3b.server.entries.EmployeeEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface EmployeeRepository
 */
@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeEntry, String> {

    /**
     * Trouve l'entrée d'employé par le courriel
     * @param mail Courriel
     * @return EmployeeEntry
     */
    EmployeeEntry findByMail(String mail);

    /**
     * Supprime l'entrée d'employé par le courriel
     * @param mail Courriel
     */
    void deleteByMail(String mail);

}
