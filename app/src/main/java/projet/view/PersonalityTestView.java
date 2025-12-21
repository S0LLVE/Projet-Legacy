package projet.view;
import projet.model.Answer;
import projet.model.PersonalityQuestion;
import projet.model.Result;
import java.util.Optional; // Ajout de l'import pour Optional
public interface PersonalityTestView {
    void showQuestion(PersonalityQuestion question);
    
    // Modifié : Retourne un Optional<Answer> pour gérer l'abandon du questionnaire
    Optional<Answer> readAnswer(); 
    
    void showResults(Result result);
    // Ajouté : Méthode pour afficher un message lorsque l'utilisateur quitte
    void displayExitMessage(); 
}