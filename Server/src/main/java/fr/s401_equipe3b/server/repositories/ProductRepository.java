/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.repositories;

import fr.s401_equipe3b.server.entries.ProductEntry;
import fr.s401_equipe3b.server.repositories.custom.CustomProductRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface ProductRepository
 */
@Repository
public interface ProductRepository extends MongoRepository<ProductEntry, String>, CustomProductRepository {

    /**
     * Vérifie si l'entrée de produit existe par le nom
     * @param name Nom
     * @return boolean
     */
    ProductEntry findByName(String name);

    /**
     * Trouve l'entrée de produit par le nom
     * @param name Nom
     * @return ProductEntry
     */
    void deleteByName(String name);

    /**
     * Vériie si l'entrée de produit existe par l'ID
     * @param productId ID
     * @return ProductEntry
     */
    ProductEntry findByProductId(int productId);

    /**
     * Supprime l'entrée de produit par l'ID
     * @param productId ID
     */
    void deleteByProductId(int productId);

}
