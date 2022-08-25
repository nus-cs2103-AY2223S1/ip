import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    private LocalDate byDate;
    private LocalTime byTime;

    Deadline(String title, String deadline) throws IllegalArgumentException {
        super(title);
        byDate = RegexHelper.extractAndParseDate(deadline)
                .orElseThrow(() -> new IllegalArgumentException("Invalid deadline date: " + deadline));
        byTime = RegexHelper.extractAndParseTime(deadline)
                .orElseThrow(() -> new IllegalArgumentException("Invalid deadline time: " + deadline));
    }

    @Override
    public String toString() {
        return String.format("‼ %s (by %s at %s)", super.toString(), byDate, byTime);
    }
}
