import java.util.*;

public class StudentParser {
    public ParsedStudent parse(String raw) {
        Map<String, String> kv = new LinkedHashMap<>();
        for (String part : raw.split(";")) {
            String[] tokens = part.split("=", 2);
            if (tokens.length == 2) {
                kv.put(tokens[0].trim(), tokens[1].trim());
            }
        }
        return new ParsedStudent(
            kv.getOrDefault("name",    ""),
            kv.getOrDefault("email",   ""),
            kv.getOrDefault("phone",   ""),
            kv.getOrDefault("program", "")
        );
    }
}
