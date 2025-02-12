/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.entries;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Counter
 */
@Document(collection = "counters")
public class Counter {

    @Id
    private String id; // Nom du compteur
    private int seq; // Valeur du compteur

    /**
     * Getter
     * @return Nom du compteur
     */
    public String getId() {
        return this.id;
    }

    /**
     * Getter
     * @return Valeur du compteur
     */
    public int getSeq() {
        return this.seq;
    }

}
