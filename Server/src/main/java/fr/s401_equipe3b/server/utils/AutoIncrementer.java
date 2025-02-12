package fr.s401_equipe3b.server.utils;

import fr.s401_equipe3b.server.entries.Counter;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

/**
 * AutoIncrementer
 */
public abstract class AutoIncrementer {

    /**
     * Récupère le prochain identifiant de la collection
     * @param operations MongoOperations
     * @param collection Nom de la collection
     * @return Identifiant suivant
     */
    public static int getNextId(MongoOperations operations, String collection) {
        final Query query = new Query(Criteria.where("_id").is(collection));
        final Update update = new Update().inc("seq", 1);
        final Counter counter = operations.findAndModify(query, update, options().returnNew(true).upsert(true), Counter.class);
        return counter != null ? counter.getSeq() : 1;
    }

}
