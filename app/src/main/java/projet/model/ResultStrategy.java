
package projet.model;
import java.util.List;
import java.util.Map;
public interface ResultStrategy {
    Map<PersonalityType, Integer> computeScores(List<Answer> answers);
}