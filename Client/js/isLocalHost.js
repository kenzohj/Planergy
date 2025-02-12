/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Modifier ici pour changer le serveur.                       *
 * http://iut.hyside.fr/ est le VPS personnel de Kenzo Hambli. *
 * S'il ne fonctionne plus, merci de lancer le serveur avec la *
 * commande 'gradlew bootRun', et de mettre la variable        *
 * 'SERVER_DOWN' à 'true'.                                     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
const IS_LOCAL = true; // Mettre à true si le serveur n'est pas fonctionnel
let URL_SERVER_NAME;
if (IS_LOCAL) URL_SERVER_NAME = "http://localhost:8080";
else          URL_SERVER_NAME = "http://iut.hyside.fr:8080";

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Cette variable représente le maximum de stockage pour une   *
 * énergie. Elle est modifiable selon les besoins.             *
 * Cela n'est pas spécifié dans le cahier des charges.         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
const MAX_ENERGY = 10000;
