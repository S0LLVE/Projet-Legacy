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
        // Si la liste des réponses est nulle, retourne les scores initialisés à zéro
        if (answers == null) {
            return scores;
        }
        // Parcourt chaque réponse pour compter les occurrences de chaque type de personnalité
        for (Answer a : answers) {
            // Vérifie que la réponse et l'option sélectionnée ne sont pas nulles
            if (a == null || a.getSelectedOption() == null) {
                continue;
            }
            Option selectedOption = a.getSelectedOption(); 
            // C'EST ICI QUE get-Type() EST APPELÉ
            PersonalityType type = selectedOption.getType();
            scores.put(type, scores.getOrDefault(type, 0) + 1);
        }
        return scores;
    }
}