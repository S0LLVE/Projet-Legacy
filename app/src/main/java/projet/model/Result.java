
package projet.model;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
public class Result {
    private final Map<PersonalityType, Integer> scores = new EnumMap<>(PersonalityType.class);
    public Result() {
        for (PersonalityType t : PersonalityType.values()) {
            scores.put(t, 0);
        }
    }
    public void addScore(PersonalityType type, int value) {
        Objects.requireNonNull(type);
        scores.put(type, scores.getOrDefault(type, 0) + value);
    }
    public Map<PersonalityType, Integer> getSummary() {
        return Collections.unmodifiableMap(scores);
    }
    public PersonalityType getWinner() {
        PersonalityType best = null;
        int max = Integer.MIN_VALUE;
        for (Map.Entry<PersonalityType, Integer> e : scores.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                best = e.getKey();
            }
        }
        return best;
    }
    @Override
    public String toString() {
        return "Result{" + "scores=" + scores + '}';
    }
}