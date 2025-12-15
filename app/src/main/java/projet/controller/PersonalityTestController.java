package projet.controller;
import projet.model.Answer;
import projet.model.Option;
import projet.model.PersonalityQuestion;
import projet.model.PersonalityType;
import projet.model.Result;
import projet.model.ResultStrategy;
import projet.view.PersonalityTestView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Contrôleur : orchestre la vue et la logique métier (stratégie de calcul).
 */
public class PersonalityTestController {
    private final List<PersonalityQuestion> questions = new ArrayList<>();
    private final PersonalityTestView view; 
    private final ResultStrategy strategy;
    public PersonalityTestController(PersonalityTestView view, ResultStrategy strategy) {
        this.view = view;
        this.strategy = strategy;
    }
    public void initQuestions() {
        // initialisation.
        questions.clear();
        List<Option> opts1 = QuestionFactory.defaultOptionsFor(PersonalityType.INTROVERSION, PersonalityType.EXTRAVERSION);
        PersonalityQuestion q1 = QuestionFactory.createQuestion("Q1", "Préférez-vous passer une soirée seul(e) ?", PersonalityType.INTROVERSION, opts1);
        List<Option> opts2 = QuestionFactory.defaultOptionsFor(PersonalityType.PLANNING, PersonalityType.CREATIVITY);
        PersonalityQuestion q2 = QuestionFactory.createQuestion("Q2", "Préférez-vous planifier vos tâches ?", PersonalityType.PLANNING, opts2);
        List<Option> opts3 = QuestionFactory.defaultOptionsFor(PersonalityType.CREATIVITY, PersonalityType.ALTRUISM);
        PersonalityQuestion q3 = QuestionFactory.createQuestion("Q3", "Aimez-vous créer de nouvelles choses ?", PersonalityType.CREATIVITY, opts3);
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
    }
    public void runTest() {
        List<Answer> answers = new ArrayList<>();
        for (PersonalityQuestion q : questions) {
            view.showQuestion(q);
            Answer a = view.readAnswer();
            if (a != null) answers.add(a);
        }
        Result result = evaluate(answers);
        displayResults(result);
    }
    public Result evaluate(List<Answer> answers) {
        Map<PersonalityType, Integer> scores = strategy.computeScores(answers);
        Result result = new Result();
        for (Map.Entry<PersonalityType, Integer> e : scores.entrySet()) {
            result.addScore(e.getKey(), e.getValue());
        }
        return result;
    }
    public void displayResults(Result result) {
        view.showResults(result);
    }
}