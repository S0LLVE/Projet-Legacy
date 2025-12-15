
package projet.model;
public class Answer {
    private final String questionId;
    private final Option selectedOption;
    public Answer(String questionId, Option selectedOption) {
        this.questionId = questionId;
        this.selectedOption = selectedOption;
    }
    public String getQuestionId() {
        return questionId;
    }
    public Option getSelectedOption() {
        return selectedOption;
    }
}