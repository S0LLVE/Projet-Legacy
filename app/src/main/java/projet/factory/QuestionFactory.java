package projet.factory;
import projet.model.PersonalityQuestion;
import projet.model.PersonalityType;
import projet.model.Option; 
import java.util.List;    
import java.util.ArrayList; 
public class QuestionFactory {
    // Méthode pour créer une question avec ID, texte, type et options
    public static PersonalityQuestion createQuestion(String id, String text, PersonalityType type, List<Option> options) {
      
        return new PersonalityQuestion(id, text, type, options);
    }

    public static List<Option> defaultOptionsFor(PersonalityType primary, PersonalityType secondary) {
        List<Option> options = new ArrayList<>();
        options.add(new Option("A", "Plutôt " + primary.name().toLowerCase(), primary, 2));
        options.add(new Option("B", "Plutôt " + secondary.name().toLowerCase(), secondary, 1));
        options.add(new Option("C", "Ne sais pas / neutre", primary, 0)); // Assumons le primary pour l'option neutre ou adapte si nécessaire
        return options;
    }

}