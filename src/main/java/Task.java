import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    public final TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public Integer getDoneStatus() {
        if (this.isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDateEqual(LocalDate date) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
