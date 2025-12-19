// Dans projet/view/ConsolePersonalityTestView.java
package projet.view;
import projet.model.Answer;
import projet.model.Option;
import projet.model.PersonalityQuestion;
import projet.model.Result;
import java.util.List;
import java.util.Scanner;
/**
 * Implémentation console. readAnswer() se base sur la dernière question affichée.
 */
public class ConsolePersonalityTestView implements PersonalityTestView {
    private final Scanner scanner = new Scanner(System.in);
    private PersonalityQuestion lastQuestion;
    @Override
    public void showQuestion(PersonalityQuestion question) {
        this.lastQuestion = question;
        System.out.println();
        System.out.println("Question " + question.getId() + ": " + question.getText());
        List<Option> options = question.getOptions();
        for (Option o : options) {
            // Pas de changement ici par rapport à ma proposition précédente, mais c'est la bonne ligne.
            // o.toString() est maintenant juste le texte de l'option.
            System.out.println(o.getId() + ") " + o.toString());
        }
        System.out.print("Votre choix (id de l'option) : ");
    }
    @Override
    public Answer readAnswer() {
        if (lastQuestion == null) {
            System.err.println("Erreur: Aucune question n'a été affichée avant de demander une réponse.");
            return null;
        }
        String input = scanner.nextLine().trim();
        Option selected = null;
        for (Option o : lastQuestion.getOptions()) {
            if (o.getId().equalsIgnoreCase(input)) {
                selected = o;
                break;
            }
        }
        if (selected == null) {
            System.out.println("Choix invalide. Veuillez entrer l'ID d'une option valide (A, B, C, etc.).");
            if (!lastQuestion.getOptions().isEmpty()) {
                selected = lastQuestion.getOptions().get(0);
                System.out.println("Première option sélectionnée par défaut : " + selected.getId());
            } else {
                System.err.println("Erreur: La question n'a aucune option disponible. Impossible de fournir une réponse.");
                return null;
            }
        }
        return new Answer(lastQuestion.getId(), selected);
    }
    @Override
    public void showResults(Result result) {
        System.out.println();
        // CORRECTION des guillemets typographiques ici
        System.out.println("--- Résultats ---");
        result.getSummary().forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("Type dominant: " + result.getWinner());
    }
}