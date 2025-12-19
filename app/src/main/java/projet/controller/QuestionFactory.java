// Dans projet/controller/QuestionFactory.java
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
     * Crée une liste d'options par défaut pour une question.
     * Les textes des options sont des réponses génériques : "Oui", "Non", "Je ne sais pas".
     *
     * @param primary Le type de personnalité principal pour l'option affirmative (Oui).
     * @param secondary Le type de personnalité secondaire pour l'option négative (Non).
     * @return Une liste d'objets Option.
     */
    public static List<Option> defaultOptionsFor(PersonalityType primary, PersonalityType secondary) {
        return Arrays.asList(
                // Correction des guillemets typographiques ("«" et "»" remplacés par "\"")
                // et modification du texte de l'option pour "Oui"
                new Option("A", "Oui", primary, 2),
                // Correction des guillemets typographiques et modification du texte de l'option pour "Non"
                new Option("B", "Non", secondary, 1),
                // Correction des guillemets typographiques et modification du texte de l'option pour "Je ne sais pas"
                new Option("C", "Je ne sais pas", primary, 0)
        );
    }
}