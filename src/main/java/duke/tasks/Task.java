package duke.tasks;

/**
 * Used to represent any type of tasks, to do, deadline, or events and their relevant information
 */
public abstract class Task {
    protected boolean isDone;
    protected String description;

    /**
     * Constructor of Task
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks a task; marks a task as undone
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Get string representation of whether task is marked
     * @return "X" if task is marked, " " if task is not marked
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Get description of task
     * @return description the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get corresponding letter tag for each type of task
     * @return "T" if to do, "E" if event, "D" if deadline
     */
    public abstract String getLetterTag();

    /**
     * Get deadline from deadlines and event timings from events
     * @return string representing deadline for deadlines, event timings for events, "" for to do
     */
    public abstract String getAdditionalInfo();

}
