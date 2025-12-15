package projet.view;
import projet.model.Answer;
import projet.model.PersonalityQuestion;
import projet.model.Result;
public interface PersonalityTestView {
    void showQuestion(PersonalityQuestion question);
    Answer readAnswer();
    void showResults(Result result);
}