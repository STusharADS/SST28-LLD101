import java.util.*;

public class ValidationResult {
    private final List<String> errors;

    public ValidationResult(List<String> errors) {
        this.errors = Collections.unmodifiableList(new ArrayList<>(errors));
    }

    public boolean isValid()          { return errors.isEmpty(); }
    public List<String> getErrors()   { return errors; }
}
