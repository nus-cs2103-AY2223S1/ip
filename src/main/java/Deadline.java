import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(boolean isDone, String text, LocalDateTime deadline, boolean isPrinting) {
        super(isDone, text, isPrinting);
        this.deadline = deadline;
        if (isPrinting) {
            System.out.println(this.toString());
        }
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")));
    }
}