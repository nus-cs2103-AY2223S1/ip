import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;

    public Event(boolean isDone, String text, LocalDateTime time, boolean isPrinting) {
        super(isDone, text, isPrinting);
        this.time = time;
        if (isPrinting) {
            System.out.println(this.toString());
        }
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.time.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")));
    }
}