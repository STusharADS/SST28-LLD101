import java.util.*;

public class EligibilityEngine {
    private final List<EligibilityRule>  rules;
    private final FakeEligibilityStore   store;

    public EligibilityEngine(List<EligibilityRule> rules, FakeEligibilityStore store) {
        this.rules = rules;
        this.store = store;
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        for (EligibilityRule rule : rules) {
            Optional<String> failure = rule.check(s);
            if (failure.isPresent())
                return new EligibilityEngineResult("NOT_ELIGIBLE", List.of(failure.get()));
        }
        return new EligibilityEngineResult("ELIGIBLE", List.of());
    }

    public void runAndPrint(StudentProfile s) {
        EligibilityEngineResult r = evaluate(s);
        new ReportPrinter().print(s, r);
        store.save(s.rollNo, r.status);
    }
}
