import java.util.*;

public class StudentValidator {
    private static final Set<String> ALLOWED_PROGRAMS = Set.of("CSE", "AI", "SWE");

    public ValidationResult validate(ParsedStudent s) {
        List<String> errors = new ArrayList<>();

        if (s.name.isBlank())
            errors.add("name is required");

        if (s.email.isBlank() || !s.email.contains("@"))
            errors.add("email is invalid");

        if (s.phone.isBlank() || !s.phone.chars().allMatch(Character::isDigit))
            errors.add("phone is invalid");

        if (!ALLOWED_PROGRAMS.contains(s.program))
            errors.add("program is invalid");

        return new ValidationResult(errors);
    }
}
