/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.body;

/**
 * Corps de la requête de vérification lors de la connexion d'un employé.
 */
public record EmployeeVerifyBody(
        String mail,
        String password) {
}
