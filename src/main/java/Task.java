import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    protected LocalDate processDate(String dt) {
        try {
            return LocalDate.parse(dt);
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("☹ OOPS!!! This date format is invalid. (YYYY-MM-DD)");
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
