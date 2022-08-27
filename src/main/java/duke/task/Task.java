package duke.task;

import java.time.LocalDateTime;

/**
 * Represents the parent class for Event, Deadline and ToDo
 */
public class Task {
    private static final String MARKED_TXT = "Nice ! I've marked this task as done:";
    private static final String UNMARKED_TEXT = "OK, I've marked this task as not done yet:";
    public final TaskType type;
    private String description;
    private boolean isDone;
    private LocalDateTime date;

    /**
     * Constructs a Task instance.
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
    public String markAsDone(boolean slient) {
        this.isDone = true;
        if (!slient) {
            return determineTextOutput() + "\n" + this;
        }
        return "";
    }

    /**
     * Mark a task as undone, outputs status of action.
     * @return
     */
    public String markAsUnDone() {
        this.isDone = false;
        return determineTextOutput() + "\n" + this;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDoneStatus() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns the LocalDateTime in string format
     * @return string of LocalDateTime stored in Task instance.
     */
    public String getBy() {
        return "";
    }

    /**
     *  Returns the string that describes a particular task
     */
    @Override
    public String toString() {
        String toPrint = String.format("%s %s", this.getMarkedStatus(), this.description);
        return toPrint;
    }
}
