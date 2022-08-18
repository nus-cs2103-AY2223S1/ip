package duke.task;

import java.time.LocalDateTime;

public class Task {
    private String description;
    private boolean isDone;
    private LocalDateTime date;
    public final TaskType type;
    private static String MARKED_TXT = "Nice ! I've marked this task as done:";
    private static String UNMARKED_TEXT = "OK, I've marked this task as not done yet:";

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.date = LocalDateTime.now();
    }

    protected Task(String description, TaskType type, LocalDateTime date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
        this.type = type;
    }

    private String getMarkedStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    private String determineTextOutput() {
        return isDone ? MARKED_TXT : UNMARKED_TEXT;
    }

    public void markAsDone(boolean slient) {
        this.isDone = true;
        if (!slient) System.out.println(determineTextOutput() + "\n" + this + "\n");
    }

    public void markAsUnDone() {
        this.isDone = false;
        System.out.println(determineTextOutput() + "\n" + this + "\n");
    }

    public String getDescription() {
        return this.description;
    }

    public int getDoneStatus() {
        return isDone ? 1 : 0;
    }

    public String getBy() {
        return "";
    }

    @Override
    public String toString() {
        String toPrint = String.format("%s %s", this.getMarkedStatus(), this.description);
        return toPrint;
    }
}
