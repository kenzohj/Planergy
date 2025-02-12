/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.services;

import fr.s401_equipe3b.server.entries.EnergyEntry;
import fr.s401_equipe3b.server.entries.ProductEntry;
import fr.s401_equipe3b.server.entries.RestockEntry;
import fr.s401_equipe3b.server.repositories.RestockRepository;
import fr.s401_equipe3b.server.utils.RestockStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Gestion des réapprovisionnements
 */
@Service
public class RestockService {

    // Repository des réapprovisionnements
    @Autowired
    private RestockRepository repository;

    // Services des produits et énergies pour mettre à jour les stocks
    @Autowired
    private ProductService productService;
    @Autowired
    private EnergyService energyService;

    /**
     * Récupérer les réapprovisionnements
     * @return Réapprovisionnements
     */
    public List<RestockEntry> getAll() {
        return this.repository.findAll();
    }

    /**
     * Récupérer un réapprovisionnement par son identifiant
     * @param id Identifiant du réapprovisionnement
     * @return Réapprovisionnement
     */
    public RestockEntry get(int id) {
        return this.repository.findByRestockId(id);
    }

    /**
     * Créer un réapprovisionnement
     * @param userId Identifiant de l'utilisateur
     * @param date Date du réapprovisionnement
     * @param order Commande du réapprovisionnement
     * @param amount Montant du réapprovisionnement
     * @return Réapprovisionnement
     */
    public RestockEntry create(String userId, String date, Map<String, Integer> order, double amount) {
        return this.repository.save(new RestockEntry(this.repository.getNextRestockId(), userId, RestockStatus.PENDING, date, "", order, amount));
    }

    /**
     * Supprimer un réapprovisionnement
     * @param id Identifiant du réapprovisionnement
     * @return Réapprovisionnement
     */
    public RestockEntry delete(int id) {
        final RestockEntry entry = this.repository.findByRestockId(id);
        if(entry == null) return null;
        this.repository.deleteByRestockId(id);
        return entry;
    }

    /**
     * Mettre à jour le statut d'un réapprovisionnement
     * @param id Identifiant du réapprovisionnement
     * @param status Statut du réapprovisionnement
     * @return Réapprovisionnement
     */
    public RestockEntry updateStatus(int id, RestockStatus status) {
        final RestockEntry entry = this.repository.findByRestockId(id);
        if(entry == null) return null;
        if(status == RestockStatus.ARRIVED) updateAmounts(entry.getOrder());
        this.repository.deleteByRestockId(id);
        return this.repository.save(new RestockEntry(id, entry.getUserId(), status, entry.getDate(), entry.getArrivalDate(), entry.getOrder(), entry.getAmount()));
    }

    /**
     * Mettre à jour la date d'arrivée d'un réapprovisionnement
     * @param id Identifiant du réapprovisionnement
     * @param arrivalDate Date d'arrivée du réapprovisionnement
     * @return Réapprovisionnement
     */
    public RestockEntry updateArrivalDate(int id, String arrivalDate) {
        final RestockEntry entry = this.repository.findByRestockId(id);
        if(entry == null) return null;
        this.repository.deleteByRestockId(id);
        return this.repository.save(new RestockEntry(id, entry.getUserId(), entry.getStatus(), entry.getDate(), arrivalDate, entry.getOrder(), entry.getAmount()));
    }

    /**
     * Mettre à jour les montants des produits et énergies commandés
     * @param order Commande
     */
    private void updateAmounts(Map<String, Integer> order) {
        for(Map.Entry<String, Integer> entry : order.entrySet()) {
            final ProductEntry pe = this.productService.get(entry.getKey());
            if(pe != null) {
                this.productService.updateQuantity(entry.getKey(), pe.getQuantity() + entry.getValue());
                continue;
            }
            final EnergyEntry ee = this.energyService.get(entry.getKey());
            if(ee != null)
                this.energyService.updateQuantity(entry.getKey(), ee.getQuantity() + entry.getValue());
        }
    }

}
