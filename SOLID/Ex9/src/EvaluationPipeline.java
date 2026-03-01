public class EvaluationPipeline {
    private final PlagiarismDetector plagiarismDetector;
    private final Grader grader;
    private final ReportGenerator reportGenerator;
    private final Rubric rubric;

    public EvaluationPipeline(PlagiarismDetector plagiarismDetector,
                              Grader grader,
                              ReportGenerator reportGenerator,
                              Rubric rubric) {
        this.plagiarismDetector = plagiarismDetector;
        this.grader = grader;
        this.reportGenerator = reportGenerator;
        this.rubric = rubric;
    }

    public void evaluate(Submission sub) {
        int plag = plagiarismDetector.check(sub);
        System.out.println("PlagiarismScore=" + plag);

        int code = grader.grade(sub, rubric);
        System.out.println("CodeScore=" + code);

        String reportName = reportGenerator.write(sub, plag, code);
        System.out.println("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        System.out.println("FINAL: " + result + " (total=" + total + ")");
    }
}
