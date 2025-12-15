
package projet.model;
import java.util.Collections;
import java.util.List;
public class PersonalityQuestion {
    private final String id;
    private final String text;
    private final PersonalityType type; 
    private final List<Option> options;
    public PersonalityQuestion(String id, String text, PersonalityType type, List<Option> options) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.options = Collections.unmodifiableList(options);
    }
    public String getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public PersonalityType getType() {
        return type;
    }
    public List<Option> getOptions() {
        return options;
    }
}