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
        if (answers == null) return scores;
        for (Answer a : answers) {
            if (a == null || a.getSelectedOption() == null) continue;
            Option o = a.getSelectedOption(); 
            PersonalityType t = o.getType(); 
            
            scores.put(t, scores.getOrDefault(t, 0) + o.getScore()); 
        }
        return scores;
    }
}