/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.services;

import fr.s401_equipe3b.server.entries.EmployeeEntry;
import fr.s401_equipe3b.server.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Gestion des employés
 */
@Service
public class EmployeeService {

    // Repository des employés
    @Autowired
    private EmployeeRepository repository;

    /**
     * Récupérer les employés
     * @return Employés
     */
    public List<EmployeeEntry> getAll() {
        return this.repository.findAll();
    }

    /**
     * Récupérer un employé par son mail
     * @param mail Mail de l'employé
     * @return Employé
     */
    public EmployeeEntry get(String mail) {
        return this.repository.findByMail(mail);
    }

    /**
     * Créer un employé
     * @param mail Mail de l'employé
     * @param password Mot de passe de l'employé
     * @param firstName Prénom de l'employé
     * @param lastName Nom de l'employé
     * @param isAdmin Employé administrateur
     * @return Employé
     */
    public EmployeeEntry create(String mail, String password, String firstName, String lastName, boolean isAdmin) {
        if(this.get(mail) != null) return null;
        final EmployeeEntry entry = new EmployeeEntry(mail, password, firstName, lastName, isAdmin, false);
        return this.repository.save(entry);
    }

    /**
     * Valider un compte
     * @param mail Mail de l'employé
     * @return Employé
     */
    public EmployeeEntry validate(String mail) {
        final EmployeeEntry entry = this.get(mail);
        if(entry == null) return null;
        this.delete(mail);
        return this.repository.save(new EmployeeEntry(entry.getMail(), entry.getHash(), entry.getSalt(), entry.getFirstName(), entry.getLastName(), entry.isAdmin(), true));
    }

    /**
     * Mettre à jour un employé
     * @param oldMail Ancien mail de l'employé
     * @param mail Nouveau mail de l'employé
     * @param password Mot de passe de l'employé
     * @param firstName Prénom de l'employé
     * @param lastName Nom de l'employé
     * @param isAdmin Employé administrateur
     * @return Employé
     */
    public EmployeeEntry update(String oldMail, String mail, String password, String firstName, String lastName, boolean isAdmin) {
        if(this.get(oldMail) == null) return null;
        this.repository.deleteByMail(oldMail);
        return this.repository.save(new EmployeeEntry(mail, password, firstName, lastName, isAdmin, true));
    }

    /**
     * Supprimer un employé
     * @param mail Mail de l'employé
     * @return Employé
     */
    public boolean delete(String mail) {
        if(this.get(mail) == null) return false;
        this.repository.deleteByMail(mail);
        return true;
    }

}
