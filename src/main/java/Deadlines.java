import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private LocalDateTime deadline;
    public Deadlines(String task, String deadline) throws DukeException {
        super(task);
        this.deadline = ConvertDateTime(deadline);
    }

    public Deadlines(String task, String deadline, boolean done) {
        super(task, done);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline.format(format));
    }

    @Override
    public String toSaveString() {
        return "deadline " + super.toSaveString() + deadline;
    }
}
