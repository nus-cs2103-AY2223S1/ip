import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;
    private static String MARKED_TXT = "Nice ! I've marked this task as done:";
    private static String UNMARKED_TEXT = "OK, I've marked this task as not done yet:";

    Task(String description) {
        this.description = description;
        this.isDone = false;
        this.date = LocalDate.now();
    }

    Task(String description, LocalDate date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
    }

    String getMarkedStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    String determineTextOutput() {
        return isDone ? MARKED_TXT : UNMARKED_TEXT;
    }

    void markAsDone() {
        this.isDone = true;
        System.out.println(determineTextOutput() + "\n" + this + "\n");
    }

    void markAsUnDone() {
        this.isDone = false;
        System.out.println(determineTextOutput() + "\n" + this + "\n");
    }

    @Override
    public String toString() {
        String toPrint = String.format("%s %s", this.getMarkedStatus(), this.description);
        return toPrint;
    }
}
