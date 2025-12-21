
package projet.view;
import projet.model.Answer;
import projet.model.Option;
import projet.model.PersonalityQuestion;
import projet.model.PersonalityType;
import projet.model.Result;
import java.util.List;
import java.util.Map;
import java.util.Optional; // Ajouté pour Optional
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
        for (Option option : options) {
            System.out.println(option.getId() + ". " + option.getText());
        }
        // Ajout de l'option de quitter
        System.out.println("Q. Quitter le questionnaire");
        System.out.print("Votre choix (id de l'option, ex: A, B, C, Q) : ");
    }
    @Override
    public Optional<Answer> readAnswer() { // La signature a changé pour Optional<Answer>
        if (lastQuestion == null) {
            System.err.println("Erreur: Aucune question n'a été affichée avant de demander une réponse.");
            return Optional.empty(); // Retourne Optional.empty() en cas d'erreur grave
        }
        String input;
        Option selected = null;
        boolean validInput = false;
        while (!validInput) {
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Q")) { // L'utilisateur veut quitter
                return Optional.empty();
            }
            for (Option o : lastQuestion.getOptions()) {
                if (o.getId().equalsIgnoreCase(input)) {
                    selected = o;
                    validInput = true;
                    break;
                }
            }
            if (!validInput) {
                System.out.println("Choix invalide. Veuillez entrer l'ID d'une option valide (ex: A, B, C) ou 'Q' pour quitter.");
                System.out.print("Votre choix (id de l'option, ex: A, B, C, Q) : ");
            }
        }
        
        // Assurez-vous que le constructeur de Answer(String questionId, Option selectedOption) existe.
        // Si 'selected' est null ici, c'est une erreur logique car validInput devrait être true.
        return Optional.of(new Answer(lastQuestion.getId(), selected));
    }
    @Override
    public void showResults(Result result) {
        System.out.println();
        System.out.println("--- Résultats ---");
        if (result.getSummary() != null && !result.getSummary().isEmpty()) { // Vérifier si le résumé n'est pas vide
            System.out.println("Scores détaillés:");
            for (Map.Entry<PersonalityType, Integer> entry : result.getSummary().entrySet()) {
                System.out.println(entry.getKey().name() + " : " + entry.getValue() + " points");
            }
        } else {
            System.out.println("Aucun résumé des scores disponible ou calculé.");
        }
        
        PersonalityType winner = result.getWinner(); // Utilisons la méthode getWinner qui retourne directement PersonalityType
        if (winner != null) {
            System.out.println("\nType dominant: " + winner.name()); // Utilisez .name() pour le nom de l'enum
            System.out.println(getDescriptionForPersonalityType(winner));
        } else {
            System.out.println("Aucun type dominant n'a été déterminé.");
        }
    }
    @Override
    public void displayExitMessage() {
        System.out.println("\n--- Questionnaire terminé ---");
        System.out.println("Vous avez choisi de quitter le questionnaire. Au revoir !");
        scanner.close(); // Ferme le scanner quand le questionnaire est terminé
    }
    // Méthode utilitaire pour donner une description basée sur le type de personnalité
    private String getDescriptionForPersonalityType(PersonalityType type) {
        switch (type) {
            case INTROVERSION:
                return "Vous êtes une personne plutôt réservée, qui apprécie le calme et les réflexions profondes. Vous rechargez vos batteries en solitude.";
            case EXTRAVERSION:
                return "Vous êtes une personne sociable et dynamique, qui tire son énergie des interactions avec les autres. Vous aimez être au centre de l'action.";
            case PLANNING:
                return "Vous êtes organisé(e) et méthodique. Vous aimez planifier et avoir une structure claire pour atteindre vos objectifs.";
            case CREATIVITY:
                return "Vous êtes imaginatif(ve) et innovant(e). Vous aimez explorer de nouvelles idées et trouver des solutions originales.";
            case ALTRUISM:
                return "Vous êtes une personne compatissante et généreuse, motivée par le bien-être d’autrui. Vous cherchez à aider et à soutenir votre entourage.";
            default:
                return "Description non disponible pour ce type de personnalité.";
        }
    }
}