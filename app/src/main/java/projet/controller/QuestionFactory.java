package projet.controller;
import java.util.Arrays;
import java.util.List;
// Importation des classes du modèle avec leur chemin complet
import projet.model.Option;
import projet.model.PersonalityQuestion;
import projet.model.PersonalityType;
public class QuestionFactory {
    /**
     * Crée une nouvelle instance de PersonalityQuestion.
     * @param id L'identifiant unique de la question.
     * @param text Le texte de la question.
     * @param type Le type de personnalité principal associé à la question (peut être utilisé pour le filtrage).
     * @param options La liste des options de réponse pour cette question.
     * @return Une instance de PersonalityQuestion.
     */
    public static PersonalityQuestion createQuestion(String id, String text, PersonalityType type, List<Option> options) {
        return new PersonalityQuestion(id, text, type, options);
    }
    /**

     *
     * @param primary Le type de personnalité principal pour l'option forte.
     * @param secondary Le type de personnalité secondaire pour l'option plus faible.
     * @return Une liste d'objets Option.
     */
    public static List<Option> defaultOptionsFor(PersonalityType primary, PersonalityType secondary) {
        return Arrays.asList(
                new Option("A", "Plutôt " + primary.name().toLowerCase(), primary, 2),
                new Option("B", "Plutôt " + secondary.name().toLowerCase(), secondary, 1),
                new Option("C", "Ne sais pas / neutre", primary, 0) 
        );
    }
}