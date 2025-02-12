package fr.s401_equipe3b.server.body;

/**
 * Corps de la requête de mise à jour de la date d'arrivée d'un réapprovisionnement.
 */
public record RestockUpdateArrivalDateBody(int id,
                                           String arrivalDate) {
}
