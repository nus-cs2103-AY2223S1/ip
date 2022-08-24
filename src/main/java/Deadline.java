import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDateTime time;

    public Deadline(String command, LocalDateTime time) {
        super(command);
        this.time = time;
    }

    @Override
    public String toString() {
        if (done) {
            return "[D][X] " + this.description + " (by: " +
                    this.time.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a")) + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " +
                    this.time.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a")) + ")";
        }
    }

    @Override
    public String toStringText() {
        return "D | " + this.done + " | " + this.description + " | " + this.time;
    }
}