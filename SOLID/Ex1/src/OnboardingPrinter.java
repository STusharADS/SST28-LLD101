public class OnboardingPrinter {

    public void printInputEcho(String raw) {
        System.out.println("INPUT: " + raw);
    }

    public void printErrors(ValidationResult result) {
        System.out.println("ERROR: cannot register");
        for (String error : result.getErrors()) {
            System.out.println("- " + error);
        }
    }

    public void printConfirmation(StudentRecord rec, int totalCount) {
        System.out.println("OK: created student " + rec.id);
        System.out.println("Saved. Total students: " + totalCount);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}
