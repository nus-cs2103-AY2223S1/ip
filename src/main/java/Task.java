import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime date;
    final TaskType type;
    private static String MARKED_TXT = "Nice ! I've marked this task as done:";
    private static String UNMARKED_TEXT = "OK, I've marked this task as not done yet:";

    Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.date = LocalDateTime.now();
    }

    Task(String description, TaskType type, LocalDateTime date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
        this.type = type;
    }

    String getMarkedStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    String determineTextOutput() {
        return isDone ? MARKED_TXT : UNMARKED_TEXT;
    }

    void markAsDone(boolean slient) {
        this.isDone = true;
        if (!slient) System.out.println(determineTextOutput() + "\n" + this + "\n");
    }

    void markAsUnDone() {
        this.isDone = false;
        System.out.println(determineTextOutput() + "\n" + this + "\n");
    }

    String getBy() {
        return "";
    }

    @Override
    public String toString() {
        String toPrint = String.format("%s %s", this.getMarkedStatus(), this.description);
        return toPrint;
    }
}
