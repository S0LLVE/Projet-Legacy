// Dans projet/view/ConsolePersonalityTestView.java
package projet.view;
import projet.model.Answer;
import projet.model.Option;
import projet.model.PersonalityQuestion;
import projet.model.PersonalityType;
import projet.model.Result;
import java.util.List;
import java.util.Scanner;
import java.util.Map; // Import pour Map
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
        
        // Affichage des options :
        // Maintenant que Option.toString() retourne seulement le texte,
        // nous pouvons soit utiliser forEach(System.out::println) qui appellera toString(),
        // soit explicitement afficher l'ID et le texte.
        // Je préfère afficher explicitement ID. Texte pour la clarté de l'interface utilisateur.
        for (Option option : options) {
            System.out.println(option.getId() + ". " + option.getText()); 
        }
        System.out.print("Votre choix (id de l'option, ex: A, B, C) : ");
    }
    @Override
    public Answer readAnswer() {
        if (lastQuestion == null) {
            System.err.println("Erreur: Aucune question n'a été affichée avant de demander une réponse.");
            return null; // Ou jeter une RuntimeException
        }
        String input = scanner.nextLine().trim();
        Option selected = null;
        for (Option o : lastQuestion.getOptions()) {
            // o.getId() est maintenant valide grâce à Option.java
            if (o.getId().equalsIgnoreCase(input)) {
                selected = o;
                break;
            }
        }
        if (selected == null) {
            System.out.println("Choix invalide. Veuillez entrer l'ID d'une option valide (ex: A, B, C).");
            
            // Pour le moment, sélectionne la première option par défaut si l'entrée est invalide.
            // Une meilleure approche serait de boucler tant que l'entrée est invalide.
            if (!lastQuestion.getOptions().isEmpty()) {
                selected = lastQuestion.getOptions().get(0); // Sélectionne la première option par défaut
                System.out.println("Première option sélectionnée par défaut : " + selected.getId() + ". " + selected.getText());
            } else {
                System.err.println("Erreur: La question n'a aucune option disponible. Impossible de fournir une réponse.");
                return null;
            }
        }
        
        // Assurez-vous que le constructeur de Answer(String questionId, Option selectedOption) existe.
        return new Answer(lastQuestion.getId(), selected);
    }
    @Override
    public void showResults(Result result) {
        System.out.println();
        // CORRECTION des guillemets typographiques
        System.out.println("--- Résultats ---"); 
        
        // Utilisation de Map.Entry pour parcourir les scores et affichage plus clair
        if (result.getSummary() != null) {
            System.out.println("Scores détaillés:");
            for (Map.Entry<PersonalityType, Integer> entry : result.getSummary().entrySet()) {
                System.out.println(entry.getKey().name() + " : " + entry.getValue() + " points");
            }
        } else {
            System.out.println("Aucun résumé des scores disponible.");
        }
        // Assurez-vous que getWinner() retourne un String ou a un toString() approprié
        if (result.getWinner() != null) {
            System.out.println("Type dominant: " + result.getWinner());
        } else {
            System.out.println("Aucun type dominant n'a été déterminé.");
        }
        // Ajout de la description du type de personnalité dominant pour une meilleure expérience utilisateur.
        // Supposons que getWinner() retourne un PersonalityType ou son nom sous forme de String.
        Object winner = result.getWinner();
        if (winner instanceof PersonalityType) {
            System.out.println(getDescriptionForPersonalityType((PersonalityType) winner));
        } else if (winner != null) {
            try {
                // Tenter de convertir un String en PersonalityType
                PersonalityType winnerType = PersonalityType.valueOf(winner.toString().toUpperCase());
                System.out.println(getDescriptionForPersonalityType(winnerType));
            } catch (IllegalArgumentException e) {
                // Le winner n'est pas un nom de PersonalityType valide
                // System.err.println("Erreur: Le type de personnalité gagnant n'est pas reconnu pour la description.");
            }
        }
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
                return "Vous êtes une personne compatissante et généreuse, motivée par le bien-être d'autrui. Vous cherchez à aider et à soutenir votre entourage.";
            default:
                return "Description non disponible pour ce type de personnalité.";
        }
    }
}