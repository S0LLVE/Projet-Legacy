// Dans projet/model/Option.java
package projet.model;
public class Option {
    private final String id;
    private final String text; // Le texte qui sera affiché
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
        // CORRECTION 1 : Remplacement des guillemets typographiques par des guillemets standards.
        // CORRECTION 2 : La méthode toString() ne retourne PLUS la partie "(Score: ..., Type: ...)"
        return text; // Retourne uniquement le texte de l'option pour un affichage concis.
    }
    // Ajout d'une méthode pour l'affichage détaillé si nécessaire (facultatif mais recommandé)
    public String toDetailedString() {
        return id + ") " + text + " (Score: " + score + ", Type: " + type + ")";
    }
}