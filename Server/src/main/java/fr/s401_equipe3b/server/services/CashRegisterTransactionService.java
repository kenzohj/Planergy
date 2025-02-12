/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.services;

import fr.s401_equipe3b.server.entries.CashRegisterTransactionEntry;
import fr.s401_equipe3b.server.entries.EnergyEntry;
import fr.s401_equipe3b.server.entries.ProductEntry;
import fr.s401_equipe3b.server.repositories.CashRegisterTransactionRepository;
import fr.s401_equipe3b.server.utils.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Gestion des transactions de caisse
 */
@Service
public class CashRegisterTransactionService {

    // Repository des transactions de caisse
    @Autowired
    private CashRegisterTransactionRepository repository;

    // Services des produits et énergies pour mettre à jour les stocks
    @Autowired
    private ProductService productService;
    @Autowired
    private EnergyService energyService;
    @Autowired
    private CustomerService customerService;

    /**
     * Récupérer une transaction de caisse
     * @param id ID de la transaction
     * @return Transaction de caisse
     */
    public CashRegisterTransactionEntry get(int id) {
        return this.repository.findByTransactionId(id);
    }

    /**
     * Récupérer toutes les transactions de caisse
     * @return Transactions de caisse
     */
    public List<CashRegisterTransactionEntry> getAll() {
        return this.repository.findAll();
    }

    /**
     * Créer une transaction de caisse
     * @param mail Mail
     * @param customerId ID du client
     * @param date Date
     * @param amount Montant
     * @param payment Paiement
     * @param products Produits
     * @param energies Énergies
     * @return Transaction de caisse créée
     */
    public CashRegisterTransactionEntry create(String mail, int customerId, String date, double amount, Map<PaymentMethod, Double> payment, Map<String, Integer> products, Map<String, Integer> energies) {
        updateProductsQuantity(products);
        updateEnergiesQuantity(energies);
        updatePointsCustomer(customerId, amount);
        return this.repository.save(new CashRegisterTransactionEntry(this.repository.getNextTransactionId(), mail, customerId, date, amount, payment, products, energies, false));
    }

    /**
     * Valider une transaction de caisse
     * @param id ID de la transaction
     * @return Transaction de caisse validée
     */
    public CashRegisterTransactionEntry validate(int id) {
        final CashRegisterTransactionEntry entry = this.repository.findByTransactionId(id);
        if(entry == null) return null;
        this.repository.deleteByTransactionId(id);
        return this.repository.save(new CashRegisterTransactionEntry(entry.getTransactionId(), entry.getMail(), entry.getCustomerId(), entry.getDate(), entry.getAmount(), entry.getPayment(), entry.getProducts(), entry.getEnergies(), true));
    }

    /**
     * Mettre à jour les stocks des produits.
     * @param products Produits
     */
    private void updateProductsQuantity(Map<String, Integer> products) {
        for(Map.Entry<String, Integer> entry : products.entrySet()) {
            final ProductEntry productEntry = this.productService.get(entry.getKey());
            this.productService.updateQuantity(entry.getKey(), productEntry.getQuantity() - entry.getValue());
        }
    }

    /**
     * Mettre à jour les stocks des énergies.
     * @param energies Énergies
     */
    private void updateEnergiesQuantity(Map<String, Integer> energies) {
        for(Map.Entry<String, Integer> entry : energies.entrySet()) {
            final EnergyEntry energyEntry = this.energyService.get(entry.getKey());
            this.energyService.updateQuantity(entry.getKey(), energyEntry.getQuantity() - entry.getValue());
        }
    }

    /**
     * Mettre à jour les points du client.
     * @param customerId ID du client
     * @param amount Montant
     */
    private void updatePointsCustomer(int customerId, double amount) {
        int nbPoints = Math.round((float) amount/10);
        this.customerService.updatePoints(customerId, nbPoints);
    }

}
