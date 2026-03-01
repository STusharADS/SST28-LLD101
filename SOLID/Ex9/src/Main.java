public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        
        // Dependency injection: create dependencies and inject them
        PlagiarismDetector plagiarismDetector = new PlagiarismChecker();
        Grader grader = new CodeGrader();
        ReportGenerator reportGenerator = new ReportWriter();
        Rubric rubric = new Rubric();
        
        EvaluationPipeline pipeline = new EvaluationPipeline(
            plagiarismDetector,
            grader,
            reportGenerator,
            rubric
        );
        
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");
        pipeline.evaluate(sub);
    }
}
