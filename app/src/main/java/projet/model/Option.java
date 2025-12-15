package projet.model;
public class Option {
    private final String id;
    private final String text;
    private final PersonalityType type;
    private final int score; 
    public Option(String id, String text, PersonalityType type, int score) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.score = score;
    }
    // --- Getters ---
    public String getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public PersonalityType getType() {
        return type;
    }

    public int getScore() {
        return score;
    }
    @Override
    public String toString() {

        return id + ") " + text + " (Score: " + score + ", Type: " + type + ")";
    }
}