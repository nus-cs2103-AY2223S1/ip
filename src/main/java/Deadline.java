import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, boolean done, String by) {
        super(description, done);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDetail() + ")";
    }

    @Override
    public char getType() {
        return 'D';
    }

    @Override
    public String getOriginalDetail() {
        return by.toString();
    }

    @Override
    public String getFormattedDetail() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }
}