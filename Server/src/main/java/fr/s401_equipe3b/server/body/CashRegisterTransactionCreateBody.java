/* * * * * * * * * * * * * *
 * PROJET S401 - Équipe 3B *
 * * * * * * * * * * * * * *
 * BONJOUR Corentin        *
 * HAMBLI Kenzo            *
 * * * * * * * * * * * * * */
package fr.s401_equipe3b.server.body;

import fr.s401_equipe3b.server.utils.PaymentMethod;

import java.util.Map;

/**
 * Corps de la requête de création d'une transaction de caisse.
 */
public record CashRegisterTransactionCreateBody(String mail,
                                                int customerId,
                                                String date,
                                                double amount,
                                                Map<PaymentMethod, Double> payment,
                                                Map<String, Integer> products,
                                                Map<String, Integer> energies) {
}
