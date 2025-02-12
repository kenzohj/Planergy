/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.services;

import fr.s401_equipe3b.server.entries.EnergyEntry;
import fr.s401_equipe3b.server.repositories.EnergyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Gestion des énergies
 */
@Service
public class EnergyService {

    // Repository des énergies
    @Autowired
    private EnergyRepository repository;

    /**
     * Récupérer les énergies
     * @return Énergies
     */
    public List<EnergyEntry> getAll() {
        return this.repository.findAll();
    }

    /**
     * Récupérer une énergie par son nom
     * @param name Nom de l'énergie
     * @return Énergie
     */
    public EnergyEntry get(String name) {
        return this.repository.findByName(name);
    }

    /**
     * Créer une énergie
     * @param name Nom de l'énergie
     * @param price Prix de l'énergie
     * @param quantity Quantité de l'énergie
     * @param limit Limite de l'énergie
     * @return Énergie
     */
    public EnergyEntry create(String name, double price, double quantity, double limit) {
        if(this.repository.existsByName(name))
            return null;
        return this.repository.save(new EnergyEntry(name, price, quantity, limit));
    }

    /**
     * Mettre à jour une énergie
     * @param name Nom de l'énergie
     * @param quantity Quantité de l'énergie
     */
    public void updateQuantity(String name, double quantity) {
        final EnergyEntry entry = this.repository.findByName(name);
        if(entry == null) return;
        this.repository.deleteByName(name);
        this.repository.save(new EnergyEntry(entry.getName(), entry.getPrice(), quantity, entry.getLimit()));
    }

    /**
     * Mettre à jour une énergie
     * @param name Nom de l'énergie
     * @param price Prix de l'énergie
     * @param quantity Quantité de l'énergie
     * @param limit Limite de l'énergie
     * @return Énergie
     */
    public EnergyEntry update(String name, double price, double quantity, double limit) {
        final EnergyEntry entry = this.repository.findByName(name);
        if(entry == null) return null;
        this.repository.deleteByName(name);
        return this.repository.save(new EnergyEntry(name, price, quantity, limit));
    }

    /**
     * Supprimer une énergie par son nom
     * @param name Nom de l'énergie
     * @return Succès
     */
    public boolean delete(String name) {
        if(this.repository.findByName(name) == null) return false;
        this.repository.deleteByName(name);
        return true;
    }

}
