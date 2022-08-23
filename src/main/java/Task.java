import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String toFileDescription() {
        return (this.isDone ? "1 " : "0 ") + "| " + this.description;
    }

    public static Task fromFileDescription(String input) {
        return null;
    }
    public boolean isHappeningOnDate(LocalDate localDate) {
        return false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
