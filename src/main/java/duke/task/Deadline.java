package duke.task; /**
 * The Duke.Deadline class extends the Duke.Task class as it is a more specific type of task.
 */
import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    private String printDate() {
        return String.format("%s %d %d",
                this.by.getMonth().toString().substring(0, 3),
                this.by.getDayOfMonth(),
                this.by.getYear());
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String stringifyTask() {
        return String.format("%s | %s | %s", "D", super.stringifyTask(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.printDate());
    }
}
