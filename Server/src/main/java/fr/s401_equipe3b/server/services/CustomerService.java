/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.services;

import fr.s401_equipe3b.server.entries.CustomerEntry;
import fr.s401_equipe3b.server.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Gestion des clients
 */
@Service
public class CustomerService {

    // Repository des clients
    @Autowired
    private CustomerRepository repository;

    /**
     * Récupérer les clients
     * @return Clients
     */
    public List<CustomerEntry> getAll() {
        return this.repository.findAll();
    }

    /**
     * Récupérer un client par son ID
     * @param id ID du client
     * @return Client
     */
    public CustomerEntry getById(int id) {
        return this.repository.findByCustomerId(id);
    }

    /**
     * Créer un client
     * @param mail Mail du client
     * @param firstName Prénom du client
     * @param lastName Nom du client
     * @return Client
     */
    public CustomerEntry create(String mail, String firstName, String lastName) {
        if(this.repository.findByMail(mail) != null) return null;
        final CustomerEntry customer = new CustomerEntry(this.repository.getNextCustomerId(), mail, firstName, lastName, 0, null, null);
        return this.repository.save(customer);
    }

    /**
     * Ajouter des points à un client
     * @param id ID du client
     */
    public CustomerEntry addPoints(int id, int points) {
        final CustomerEntry customer = this.repository.findByCustomerId(id);
        if(customer == null) return null;
        this.repository.deleteByCustomerId(id);
        return this.repository.save(new CustomerEntry(id, customer.getMail(), customer.getFirstName(), customer.getLastName(), customer.getPoints() + points, customer.getEnergyCreditCard(), customer.getEnergyCreditCardTransactions()));
    }

    /**
     * Modifier un client
     * @param id ID du client
     * @param mail Mail du client
     * @param firstName Prénom du client
     * @param lastName Nom du client
     */
    public CustomerEntry edit(int id, String mail, String firstName, String lastName) {
        final CustomerEntry customer = this.repository.findByCustomerId(id);
        if(customer == null) return null;
        this.repository.deleteByCustomerId(id);
        return this.repository.save(new CustomerEntry(customer.getCustomerId(), mail, firstName, lastName, customer.getPoints(), customer.getEnergyCreditCard(), customer.getEnergyCreditCardTransactions()));
    }

    /**
     * Supprimer un client
     * @param id ID du client
     */
    public CustomerEntry delete(int id) {
        final CustomerEntry customer = this.repository.findByCustomerId(id);
        if(customer == null) return null;
        this.repository.deleteByCustomerId(id);
        return customer;
    }

    /**
     * Mettre à jour les points d'un client
     * @param customerId ID du client
     * @param nbPoints Nombre de points
     */
    public void updatePoints(int customerId, int nbPoints) {
        final CustomerEntry customer = this.repository.findByCustomerId(customerId);
        if(customer == null) return;
        this.repository.deleteByCustomerId(customerId);
        this.repository.save(new CustomerEntry(customerId, customer.getMail(), customer.getFirstName(), customer.getLastName(), customer.getPoints() + nbPoints, customer.getEnergyCreditCard(), customer.getEnergyCreditCardTransactions()));
    }
}
