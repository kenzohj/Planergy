/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Indique que c'est une application Spring Boot
public class Server {

    /**
     * Méthode main de l'application
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        // Lance l'application Spring Boot
        SpringApplication.run(Server.class, args);
    }

}
