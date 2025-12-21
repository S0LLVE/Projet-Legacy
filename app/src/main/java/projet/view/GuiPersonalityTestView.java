package projet.view;
import projet.model.Answer;
import projet.model.PersonalityQuestion;
import projet.model.Result;
import java.util.Optional; // Import nécessaire pour Optional
public class GuiPersonalityTestView implements PersonalityTestView {
    @Override
    public void showQuestion(PersonalityQuestion question) {
        // Pour l'instant, on laisse l'exception car la GUI n'est pas implémentée.
        // Quand la GUI sera prête, cette méthode affichera la question dans l'interface graphique.
        System.out.println("GUI: Affichage de la question: " + question.getText()); 
        throw new UnsupportedOperationException("GUI showQuestion not fully implemented yet");
    }
    @Override
    public Optional<Answer> readAnswer() {
        // Pour l'instant, on lève l'exception.
        // Quand la GUI sera prête, cette méthode attendra une action utilisateur
        // (clic sur un bouton, sélection d'une option) et retournera :
        // - Optional.of(new Answer(...)) si l'utilisateur répond.
        // - Optional.empty() si l'utilisateur clique sur un bouton "Quitter".
        System.out.println("GUI: Lecture de la réponse de l'utilisateur."); // Placeholder console
        throw new UnsupportedOperationException("GUI readAnswer not fully implemented yet");
    }
    @Override
    public void showResults(Result result) {
        // Pour l'instant, on lève l'exception.
        // Quand la GUI sera prête, cette méthode affichera les résultats dans l'interface graphique.
        System.out.println("GUI: Affichage des résultats."); // Placeholder console
        throw new UnsupportedOperationException("GUI showResults not fully implemented yet");
    }
    @Override
    public void displayExitMessage() {
        // Pour l'instant, on lève l'exception.
        // Quand la GUI sera prête, cette méthode affichera un message de sortie dans l'interface graphique.
        System.out.println("GUI: Affichage du message de sortie."); // Placeholder console
        throw new UnsupportedOperationException("GUI displayExitMessage not fully implemented yet");
    }
}