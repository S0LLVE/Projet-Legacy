package projet.model;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
public class SimpleCountStrategy implements ResultStrategy {
    @Override
    public Map<PersonalityType, Integer> computeScores(List<Answer> answers) {
        Map<PersonalityType, Integer> scores = new EnumMap<>(PersonalityType.class);
        // Initialise tous les types de personnalité à un score de 0
        for (PersonalityType t : PersonalityType.values()) {
            scores.put(t, 0);
        }
        // Si la liste des réponses est nulle ou vide, retourne les scores initialisés à zéro
        // (La vérification `answers == null` est redondante si `answers` est toujours une liste,
        // mais ne fait pas de mal pour la robustesse).
        if (answers == null || answers.isEmpty()) {
            return scores;
        }
        // Parcourt chaque réponse pour compter les occurrences de chaque type de personnalité
        for (Answer a : answers) {
            // Vérifie que la réponse et l'option sélectionnée ne sont pas nulles
            if (a == null || a.getSelectedOption() == null) {
                continue; // Passe à la réponse suivante si celle-ci est invalide
            }
            Option selectedOption = a.getSelectedOption(); 
            
            // Récupère le type de personnalité associé à l'option.
            // J'ai utilisé `getAssociatedPersonalityType()` comme discuté, si votre méthode est `getType()`,
            // assurez-vous que c'est celle qui peut retourner null pour les options neutres.
            PersonalityType type = selectedOption.getAssociatedPersonalityType(); // Assurez-vous que votre Option.java a bien cette méthode ou utilisez selectedOption.getType()
            // >>> CORRECTION ICI : Ajouter une vérification pour 'null' avant d'utiliser 'type' comme clé <<<
            if (type != null) {
                // Incrémente le score pour ce type de personnalité
                // On utilise selectedOption.getPoints() pour ajouter les points définis par l'option
                // au lieu de juste +1, ce qui est plus flexible.
                scores.put(type, scores.getOrDefault(type, 0) + selectedOption.getPoints());
            }
            // Si 'type' est null, cette option est ignorée et ne contribue pas aux scores.
        }
        return scores;
    }
}