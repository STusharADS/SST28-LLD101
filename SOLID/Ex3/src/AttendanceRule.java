import java.util.Optional;

public class AttendanceRule implements EligibilityRule {
    private final int minAttendance;

    public AttendanceRule(int minAttendance) { this.minAttendance = minAttendance; }

    @Override
    public Optional<String> check(StudentProfile s) {
        if (s.attendancePct < minAttendance)
            return Optional.of("attendance below " + minAttendance);
        return Optional.empty();
    }
}
