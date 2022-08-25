import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    Event(String title, String time) {
        this(title, time, time);
    }

    Event(String title, String start, String end) throws IllegalArgumentException {
        super(title);
        startDate = RegexHelper.extractAndParseDate(start)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event start date: " + start));
        startTime = RegexHelper.extractAndParseTime(start)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event start time: " + start));
        endDate = RegexHelper.extractAndParseDate(end)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event end date: " + end));
        endTime = RegexHelper.extractAndParseTime(end)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event end time: " + end));
    }

    @Override
    public String toString() {
        return String.format("📆 %s (from %s %s to %s %s)", super.toString(), startDate, startTime, endDate, endTime);
    }
}
