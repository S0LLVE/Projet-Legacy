package projet.controller;
import projet.model.Answer;
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
        loadQuestions(); // Charge toutes les questions dès la construction du contrôleur
    }
    /**
     * Charge toutes les questions de personnalité dans la liste 'questions'.
     * Ajusté pour les PersonalityType disponibles : INTROVERSION, EXTRAVERSION, PLANNING, CREATIVITY, ALTRUISM.
     *
     * IMPORTANT: Assurez-vous que QuestionFactory.createQuestion et
     * QuestionFactory.defaultOptionsFor existent et ont les bonnes signatures.
     * De plus, les options retournées par defaultOptionsFor doivent maintenant inclure un ID (A, B, C...).
     */
    private void loadQuestions() {
        questions.clear(); // S'assurer que la liste est vide avant de charger
        // --- 1. Introversion / Extraversion (6 questions) ---
        questions.add(QuestionFactory.createQuestion(
                "Q1", "Préférez-vous passer une soirée tranquille à la maison plutôt qu'à une grande fête ?",
                PersonalityType.INTROVERSION, QuestionFactory.defaultOptionsFor(PersonalityType.INTROVERSION, PersonalityType.EXTRAVERSION)));
        questions.add(QuestionFactory.createQuestion(
                "Q2", "Êtes-vous énergisé(e) après avoir passé du temps avec beaucoup de monde ?",
                PersonalityType.EXTRAVERSION, QuestionFactory.defaultOptionsFor(PersonalityType.EXTRAVERSION, PersonalityType.INTROVERSION)));
        questions.add(QuestionFactory.createQuestion(
                "Q3", "Aimez-vous être au centre de l'attention lors d'événements sociaux ?",
                PersonalityType.EXTRAVERSION, QuestionFactory.defaultOptionsFor(PersonalityType.EXTRAVERSION, PersonalityType.INTROVERSION)));
        questions.add(QuestionFactory.createQuestion(
                "Q4", "Préférez-vous travailler seul(e) sur un projet important plutôt qu'en équipe ?",
                PersonalityType.INTROVERSION, QuestionFactory.defaultOptionsFor(PersonalityType.INTROVERSION, PersonalityType.EXTRAVERSION)));
        questions.add(QuestionFactory.createQuestion(
                "Q5", "Vous sentez-vous souvent épuisé(e) après de longues interactions sociales ?",
                PersonalityType.INTROVERSION, QuestionFactory.defaultOptionsFor(PersonalityType.INTROVERSION, PersonalityType.EXTRAVERSION)));
        questions.add(QuestionFactory.createQuestion(
                "Q6", "Avez-vous tendance à engager la conversation avec des inconnus pour en apprendre plus ?",
                PersonalityType.EXTRAVERSION, QuestionFactory.defaultOptionsFor(PersonalityType.EXTRAVERSION, PersonalityType.INTROVERSION)));
        // --- 2. Planning / Opposé à Planning (6 questions) ---
        questions.add(QuestionFactory.createQuestion(
                "Q7", "Préfériez-vous planifier vos tâches à l'avance ou les aborder au fur et à mesure avec flexibilité ?",
                PersonalityType.PLANNING, QuestionFactory.defaultOptionsFor(PersonalityType.PLANNING, PersonalityType.CREATIVITY)));
        questions.add(QuestionFactory.createQuestion(
                "Q8", "Le désordre ou le manque d'organisation vous dérange-t-il significativement ?",
                PersonalityType.PLANNING, QuestionFactory.defaultOptionsFor(PersonalityType.PLANNING, PersonalityType.CREATIVITY)));
        questions.add(QuestionFactory.createQuestion(
                "Q9", "Vous sentez-vous plus à l'aise avec un emploi du temps structuré et des objectifs clairs ?",
                PersonalityType.PLANNING, QuestionFactory.defaultOptionsFor(PersonalityType.PLANNING, PersonalityType.EXTRAVERSION)));
        questions.add(QuestionFactory.createQuestion(
                "Q10", "Êtes-vous du genre à prendre des décisions spontanées, même si elles n'étaient pas prévues ?",
                PersonalityType.CREATIVITY, QuestionFactory.defaultOptionsFor(PersonalityType.CREATIVITY, PersonalityType.PLANNING)));
        questions.add(QuestionFactory.createQuestion(
                "Q11", "Préférez-vous que vos vacances soient planifiées en détail ou vous laissez-vous porter par les opportunités ?",
                PersonalityType.PLANNING, QuestionFactory.defaultOptionsFor(PersonalityType.PLANNING, PersonalityType.EXTRAVERSION)));
        questions.add(QuestionFactory.createQuestion(
                "Q12", "Aimez-vous l'idée de changer vos plans à la dernière minute pour une meilleure opportunité ?",
                PersonalityType.CREATIVITY, QuestionFactory.defaultOptionsFor(PersonalityType.CREATIVITY, PersonalityType.PLANNING)));
        // --- 3. Créativité / Opposé à Créativité (6 questions) ---
        questions.add(QuestionFactory.createQuestion(
                "Q13", "Aimez-vous inventer de nouvelles solutions ou de nouvelles approches aux problèmes ?",
                PersonalityType.CREATIVITY, QuestionFactory.defaultOptionsFor(PersonalityType.CREATIVITY, PersonalityType.PLANNING)));
        questions.add(QuestionFactory.createQuestion(
                "Q14", "Préférez-vous des solutions éprouvées et fiables plutôt que de chercher toujours à innover ?",
                PersonalityType.PLANNING, QuestionFactory.defaultOptionsFor(PersonalityType.PLANNING, PersonalityType.CREATIVITY)));
        questions.add(QuestionFactory.createQuestion(
                "Q15", "Trouvez-vous facile de penser en dehors des sentiers battus pour trouver des idées originales ?",
                PersonalityType.CREATIVITY, QuestionFactory.defaultOptionsFor(PersonalityType.CREATIVITY, PersonalityType.INTROVERSION)));
        questions.add(QuestionFactory.createQuestion(
                "Q16", "Privilégiez-vous l'efficacité et le respect des règles avant l'expérimentation artistique ?",
                PersonalityType.PLANNING, QuestionFactory.defaultOptionsFor(PersonalityType.PLANNING, PersonalityType.CREATIVITY)));
        questions.add(QuestionFactory.createQuestion(
                "Q17", "Êtes-vous attiré(e) par les activités artistiques, l'écriture ou la musique ?",
                PersonalityType.CREATIVITY, QuestionFactory.defaultOptionsFor(PersonalityType.CREATIVITY, PersonalityType.INTROVERSION)));
        questions.add(QuestionFactory.createQuestion(
                "Q18", "Face à un problème, préférez-vous une méthode logique et séquentielle ou une approche plus libre et intuitive ?",
                PersonalityType.PLANNING, QuestionFactory.defaultOptionsFor(PersonalityType.PLANNING, PersonalityType.CREATIVITY)));
        // --- 4. Altruisme / Opposé à Altruisme (4 questions) ---
        questions.add(QuestionFactory.createQuestion(
                "Q19", "Êtes-vous souvent le premier à offrir votre aide à ceux qui en ont besoin, même sans être sollicité ?",
                PersonalityType.ALTRUISM, QuestionFactory.defaultOptionsFor(PersonalityType.ALTRUISM, PersonalityType.INTROVERSION)));
        questions.add(QuestionFactory.createQuestion(
                "Q20", "La détresse ou les problèmes d'autrui vous touchent-ils au point de vouloir agir ?",
                PersonalityType.ALTRUISM, QuestionFactory.defaultOptionsFor(PersonalityType.ALTRUISM, PersonalityType.PLANNING)));
        questions.add(QuestionFactory.createQuestion(
                "Q21", "Quand vous entreprenez une action, votre priorité est-elle d'abord vos propres bénéfices ou ceux des autres ?",
                PersonalityType.INTROVERSION, QuestionFactory.defaultOptionsFor(PersonalityType.INTROVERSION, PersonalityType.ALTRUISM)));
        questions.add(QuestionFactory.createQuestion(
                "Q22", "Seriez-vous prêt(e) à faire des sacrifices personnels importants pour le bien d'un groupe ou d'une cause qui vous tient à cœur ?",
                PersonalityType.ALTRUISM, QuestionFactory.defaultOptionsFor(PersonalityType.ALTRUISM, PersonalityType.INTROVERSION)));
        // Total : 22 questions
    }
    public void runTest() {
        List<Answer> answers = new ArrayList<>();
        for (PersonalityQuestion q : questions) {
            view.showQuestion(q);
            Answer a = view.readAnswer(); // La vue doit retourner un Answer valide.
            if (a != null) { // Seulement si une réponse valide a été obtenue de la vue.
                // Ici, on pourrait ajouter une validation supplémentaire si nécessaire.
                answers.add(a);
            } else {
                System.err.println("La vue n'a pas pu fournir une réponse valide pour la question " + q.getId() + ".");
                // Décider comment gérer cela : continuer, ignorer la question, ou arrêter.
                // Pour l'instant, nous ignorons cette question si la vue retourne null.
            }
        }
        Result result = evaluate(answers);
        displayResults(result);
    }
    public Result evaluate(List<Answer> answers) {
        // La stratégie doit gérer l'agrégation des scores à partir des réponses.
        Map<PersonalityType, Integer> scores = strategy.computeScores(answers);
        // Crée un objet Result et peuple-le avec les scores calculés.
        Result result = new Result();
        if (scores != null) {
            for (Map.Entry<PersonalityType, Integer> e : scores.entrySet()) {
                result.addScore(e.getKey(), e.getValue());
            }
        }
        return result;
    }
    public void displayResults(Result result) {
        view.showResults(result);
    }
    // Cette méthode est potentiellement utile pour des tests ou des affichages, mais n'est pas utilisée directement dans runTest.
    public List<PersonalityQuestion> getQuestions() {
        return questions;
    }
}