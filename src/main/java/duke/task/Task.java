package duke.task;

import java.time.LocalDate;

/**
 * The abstract superclass of all types of tasks the user can input.
 */
public abstract class Task {
    /**
     * Represents the type of task it is.
     */
    public final TaskType type;
    protected String description;
    protected boolean isDone;
    private Priority priority;
    protected Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.priority = Priority.LOW;
    }

    protected Task(String description, TaskType type, Priority priority) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.priority = priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public Priority getPriority() {
        return this.priority;
    }
    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a 1 for completed tasks and 0 for uncompleted tasks for writing purposes.
     *
     * @return The integer signifying the task's status.
     */
    public Integer getDoneStatus() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Returns the description of the task for writing purposes.
     *
     * @return The string containing the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns true when the provided date matches the task's date.
     *
     * @param date The given LocalDate.
     * @return The boolean value of whether the date matches.
     */
    public abstract boolean isDateEqual(LocalDate date);

    /**
     * Returns true when the description contains the queried keywords.
     *
     * @param queries The keywords queried.
     * @return The boolean value of whether the description contains any keyword.
     */
    public boolean isQueriesPresent(String[] queries) {
        for (String query : queries) {
            if (this.description.contains(query)) {
                return true;
            }
        }
        return false;
    }

    private String priorityToString() {
        switch (priority) {
        case LOW:
            return "[L] ";
        case MEDIUM:
            return "[M] ";
        case HIGH:
            return "[H] ";
        default:
            assert false;
            break;
        }
        assert false;
        return "";
    }

    /**
     * Encodes the priority of the task into a string for writing purposes.
     * @return The string depicting the priority status.
     */
    public String priorityEncode() {
        switch (priority) {
        case LOW:
            return "LOW";
        case MEDIUM:
            return "MEDIUM";
        case HIGH:
            return "HIGH";
        default:
            assert false;
            break;
        }
        assert false;
        return "";
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X]" + priorityToString() + this.description;
        } else {
            return "[ ]" + priorityToString() + this.description;
        }
    }
}
