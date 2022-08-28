import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate deadlineDate;

    public Deadline(String description, boolean isDone, LocalDate deadlineDate) {
        super(description, isDone);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", super.getStatusIcon(),
                super.toString(), deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    @Override
    public String toFileString() {
        return String.format("D | %s | %s | %s", super.getFileIcon(),
                super.toString(), this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
