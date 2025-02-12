/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.entries;

import fr.s401_equipe3b.server.utils.PasswordUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;

/**
 * EmployeeEntry
 */
@Document(collection = "employee")
public class EmployeeEntry {

    private String mail; // Adresse mail
    private String hash; // Mot de passe hashé
    private String salt; // Sel pour le hash
    private String firstName; // Prénom
    private String lastName; // Nom
    private boolean isAdmin; // Est-ce un administrateur ?
    private boolean validated; // Est-ce un compte validé ?

    public EmployeeEntry() {} // Constructeur vide pour SpringBoot

    /**
     * Constructeur
     * @param mail Adresse mail
     * @param password Mot de passe
     * @param firstName Prénom
     * @param lastName Nom
     * @param isAdmin Est-ce un administrateur ?
     * @param validated Est-ce un compte validé ?
     */
    public EmployeeEntry(String mail, String password, String firstName, String lastName, boolean isAdmin, boolean validated) {
        this.mail = mail;
        final byte[] salt = PasswordUtils.generateSalt();
        this.salt = Base64.getEncoder().encodeToString(salt);
        this.hash = PasswordUtils.hashPassword(password, salt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.validated = validated;
    }

    /**
     * Constructeur
     * @param mail Adresse mail
     * @param hash Mot de passe hashé
     * @param salt Sel pour le hash
     * @param firstName Prénom
     * @param lastName Nom
     * @param isAdmin Est-ce un administrateur ?
     * @param validated Est-ce un compte validé ?
     */
    public EmployeeEntry(String mail, String hash, String salt, String firstName, String lastName, boolean isAdmin, boolean validated) {
        this.mail = mail;
        this.hash = hash;
        this.salt = salt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.validated = validated;
    }

    /**
     * Getter
     * @return Adresse mail
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * Getter
     * @return Mot de passe hashé
     */
    public String getHash() {
        return this.hash;
    }

    /**
     * Getter
     * @return Sel pour le hash
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Getter
     * @return Prénom
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Getter
     * @return Nom
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Getter
     * @return Est-ce un administrateur ?
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Getter
     * @return Est-ce un compte validé ?
     */
    public boolean isValidated() {
        return validated;
    }

}
