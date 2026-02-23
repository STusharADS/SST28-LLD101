public class OnboardingService {
    private final StudentParser     parser;
    private final StudentValidator  validator;
    private final StudentRepository repository;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentParser parser,
                             StudentValidator validator,
                             StudentRepository repository,
                             OnboardingPrinter printer) {
        this.parser     = parser;
        this.validator  = validator;
        this.repository = repository;
        this.printer    = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInputEcho(raw);

        ParsedStudent parsed = parser.parse(raw);
        ValidationResult result = validator.validate(parsed);

        if (!result.isValid()) {
            printer.printErrors(result);
            return;
        }

        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(
            id, parsed.name, parsed.email, parsed.phone, parsed.program);

        repository.save(rec);
        printer.printConfirmation(rec, repository.count());
    }
}
