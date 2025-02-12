/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.body;

/**
 * Corps de la requête de modification d'un employé.
 */
public record EmployeeUpdateBody(String oldMail,
                                 String mail,
                                 String password,
                                 String firstName,
                                 String lastName,
                                 boolean isAdmin) {
}
