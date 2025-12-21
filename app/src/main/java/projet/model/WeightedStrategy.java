package projet.model;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
public class WeightedStrategy implements ResultStrategy {
    @Override
    public Map<PersonalityType, Integer> computeScores(List<Answer> answers) {
        Map<PersonalityType, Integer> scores = new EnumMap<>(PersonalityType.class);
        // Initialise tous les types de personnalité à un score de 0
        for (PersonalityType t : PersonalityType.values()) {
            scores.put(t, 0);
        }
        // Si la liste des réponses est nulle ou vide, retourne les scores initialisés à zéro
        if (answers == null || answers.isEmpty()) { // Ajout de answers.isEmpty() pour la robustesse
            return scores;
        }
        // Parcourt chaque réponse pour agréger les scores
        for (Answer a : answers) {
            // Vérifie que la réponse et l'option sélectionnée ne sont pas nulles
            if (a == null || a.getSelectedOption() == null) {
                continue; // Passe à la réponse suivante si celle-ci est invalide
            }
            Option selectedOption = a.getSelectedOption(); 
            
            // Récupère le type de personnalité associé à l'option.
            // Utilisez la méthode corrigée dans Option.java
            PersonalityType type = selectedOption.getAssociatedPersonalityType(); 
            
            // Récupère les points associés à l'option.
            // Utilisez la méthode corrigée dans Option.java
            int points = selectedOption.getPoints();
            // >>> CORRECTION ICI : Ajouter une vérification pour 'null' avant d'utiliser 'type' comme clé <<<
            if (type != null) {
                // Incrémente le score pour ce type de personnalité
                scores.put(type, scores.getOrDefault(type, 0) + points); 
            }
            // Si 'type' est null, cette option est ignorée et ne contribue pas aux scores.
        }
        return scores;
    }
}