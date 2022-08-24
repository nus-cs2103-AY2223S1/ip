import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toDataFormat() {
        return "The data are as follows:";
    }

    public boolean isOn(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        /** Determines if there is an X icon to show completed */
        String doneIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", doneIcon, description);
    }
}
