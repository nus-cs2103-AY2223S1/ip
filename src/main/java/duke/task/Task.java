package duke.task;

import java.time.LocalDate;

/**
 * Task encapsulates a task containing a description and whether it is completed.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public abstract class Task {
    protected enum Priority {
        LOW, HIGH
    }
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Constructor for Task.
     *
     * @param description Description of the Deadline.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        priority = Priority.LOW;
    }

    /**
     * Marks the Task as completed.
     *
     * @return The String representation of the Task.
     */
    public String mark() {
        isDone = true;
        return toString();
    }

    /**
     * Marks the Task as uncompleted.
     *
     * @return The String representation of the Task.
     */
    public String unmark() {
        isDone = false;
        return toString();
    }

    /**
     * Checks whether the description contains the given keyword.
     *
     * @param keyword Given keyword.
     * @return Whether the description contains the given keyword.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return String detailing the Task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s", isDone ? "X" : " ", priority == Priority.HIGH ? "[!]" : "", description);
    }

    /**
     * Re-constructs a Task of specific types given data.
     *
     * @param data Data of the Task to be re-constructed.
     * @return The Task corresponding to the data.
     */
    public static Task loadTask(String data) {
        char c = data.charAt(0);
        String[] dataSplit = data.split(" \\| ", c == 'T' ? 4 : 5);
        boolean isDone = dataSplit[1].equals("1");
        Priority priority = dataSplit[2].equals("1") ? Priority.HIGH : Priority.LOW;
        String description = dataSplit[3];
        LocalDate time = dataSplit.length == 5 ? LocalDate.parse(dataSplit[4]) : null;

        switch (c) {
        case 'D':
            return new Deadline(description, isDone, priority, time);
        case 'E':
            return new Event(description, isDone, priority, time);
        default:
            return new Todo(description, isDone, priority);
        }
    }

    /**
     * Converts the Task to data to be saved.
     *
     * @return Data representing the Task.
     */
    public String saveTask() {
        return String.format("%d | %d | %s", isDone ? 1 : 0, priority == Priority.HIGH ? 1 : 0, description);
    }

    /**
     * Sets the Task to high priority.
     *
     * @return The String representation of the Task.
     */
    public String setHighPriority() {
        priority = Priority.HIGH;
        return toString();
    }
}
