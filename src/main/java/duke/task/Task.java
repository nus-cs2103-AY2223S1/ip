package duke.task;

import java.time.LocalDateTime;
import java.util.Comparator;

import duke.DukeCommand;
import duke.DukeException;

/**
 * A Task to complete.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime time;

    /**
     * Constructor for a Task.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        this(description, null);
    }

    /**
     * Constructor for a Task.
     *
     * @param description The description of the Task.
     * @param time The time related to the Task.
     */
    public Task(String description, LocalDateTime time) {
        this.description = description;
        this.isDone = false;
        this.time = time;
    }

    /**
     * Gets the completion status of the Task.
     *
     * @return A boolean representing the completion status.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Gets the String representation completion status of the Task.
     *
     * @return A String representing the completion status.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Gets the DukeCommand associated with this Task type.
     *
     * @return The DukeCommand associated with this Task type.
     */
    public DukeCommand getTaskType() {
        return DukeCommand.UNKNOWN;
    }

    /**
     * Gets the description of the Task.
     *
     * @return The description of the Task.
     */
    public String getDesc() {
        return this.description;
    }

    /**
     * Gets time data of the Task in String format.
     *
     * @return A String representing time data.
     */
    public String getTimeStr() {
        return time == null ? "" : time.toString();
    }

    /**
     * Marks this Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this Task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the appropriate Comparator according to the sort criteria.
     *
     * @param criteria The sort criteria.
     * @return A Comparator for sorting a List.
     * @throws DukeException if no valid criteria was passed.
     */
    public static Comparator<Task> getComparator(String criteria) throws DukeException {
        switch (criteria) {
        case "time":
            return (t1, t2) -> {
                if (t1.time == null && t2.time == null) {
                    // both no time data, keep sort order
                    return 0;
                }

                if (t1.time == null) {
                    // t1 has no time data, put t2 first
                    return 1;
                }

                if (t2.time == null) {
                    // t2 has no time data, put t1 first
                    return -1;
                }

                return t1.time.compareTo(t2.time);
            };
        case "type":
            return Comparator.comparing(Task::getTaskType);
        case "desc":
            return Comparator.comparing(Task::getDesc);
        case "status":
            return Comparator.comparing(Task::getStatus);
        default:
            throw new DukeException("Invalid sort criteria");
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
