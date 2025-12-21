package projet.controller;
//Imports pour le Modèle
import projet.model.ResultStrategy;
import projet.model.SimpleCountStrategy;
//Imports pour la Vue
import projet.view.ConsolePersonalityTestView;
import projet.view.PersonalityTestView;
//Import pour le Contrôleur
/*
 * Classe d’entrée principale pour lancer le test de personnalité.
 */
public class TestRunner {
    public static void main(String[] args) {
        // 1. Instanciation de la Vue :
        // La vue gère l'interaction avec l'utilisateur (affichage des questions, saisie des réponses).
        PersonalityTestView view = new ConsolePersonalityTestView();
        // 2. Instanciation de la Stratégie de Résultat :
        // La stratégie définit comment les scores de personnalité sont calculés.
        ResultStrategy strategy = new SimpleCountStrategy(); // ou new WeightedStrategy() si vous l'implémentez
        // 3. Instanciation du Contrôleur :
        // Le contrôleur orchestre l'application, utilisant la vue et la stratégie.
        // Les questions sont maintenant initialisées au moment de la création du contrôleur.
        PersonalityTestController controller = new PersonalityTestController(view, strategy);
        // 4. Lancement du test :
        // Le contrôleur exécute le processus complet du test de personnalité.
        controller.runTest();
    }
}