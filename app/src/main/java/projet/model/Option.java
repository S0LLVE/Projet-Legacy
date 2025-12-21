
package projet.model;
public class Option {
    private final String id;
    private final String text; // Le texte qui sera affiché
    private final PersonalityType associatedPersonalityType; // Renommé 'type' en 'associatedPersonalityType' pour la clarté et la compatibilité
    private final int points; // Renommé 'score' en 'points' pour la clarté et la compatibilité
    public Option(String id, String text, PersonalityType associatedPersonalityType, int points) {
        this.id = id;
        this.text = text;
        this.associatedPersonalityType = associatedPersonalityType;
        this.points = points;
    }
    //Getters
    public String getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    // Renommage du getter pour correspondre à la stratégie
    public PersonalityType getAssociatedPersonalityType() {
        return associatedPersonalityType;
    }
    // Renommage du getter pour correspondre à la stratégie
    public int getPoints() {
        return points;
    }
    @Override
    public String toString() {
        return text; // Retourne uniquement le texte de l’option pour un affichage concis.
    }
    // Ajout d’une méthode pour l’affichage détaillé si nécessaire (facultatif mais recommandé)
    public String toDetailedString() {
        return id + ") " + text + " (Points: " + points + ", Type: " + associatedPersonalityType + ")";
    }
}