package projet.view;
import projet.model.Answer;
import projet.model.PersonalityQuestion;
import projet.model.Result;
public class GuiPersonalityTestView implements PersonalityTestView {
    @Override
    public void showQuestion(PersonalityQuestion question) {
        throw new UnsupportedOperationException("GUI not implemented yet");
    }
    @Override
    public Answer readAnswer() {
        throw new UnsupportedOperationException("GUI not implemented yet");
    }
    @Override
    public void showResults(Result result) {
        throw new UnsupportedOperationException("GUI not implemented yet");
    }
}