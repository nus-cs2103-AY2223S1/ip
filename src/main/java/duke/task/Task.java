package duke.task;

import java.time.LocalDateTime;

public class Task {
    private static final String MARKED_TXT = "Nice ! I've marked this task as done:";
    private static final String UNMARKED_TEXT = "OK, I've marked this task as not done yet:";
    public final TaskType type;
    private String description;
    private boolean isDone;
    private LocalDateTime date;

    /**
     * Constructor for a Task instance.
     * @param description task information
     * @param type task type Enum
     * @see TaskType
     * @see Enum
     */
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

    /**
     * Mark a task as done, outputs status of action if needed.
     * @param slient option to slience task output in console.
     */
    public void markAsDone(boolean slient) {
        this.isDone = true;
        if (!slient) {
            System.out.println(determineTextOutput() + "\n" + this + "\n");
        }
    }

    /**
     * Mark a task as undone, outputs status of action.
     */
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
