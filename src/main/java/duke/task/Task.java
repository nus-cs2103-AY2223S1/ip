package duke.task;

import java.time.LocalDate;

/**
 * Task encapsulates a task containing a description and whether it is completed.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of the Deadline.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Returns the String representation of the Task.
     *
     * @return String detailing the Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", (isDone ? "X" : " "), description);
    }

    /**
     * Re-constructs a Task of specific types given data.
     *
     * @param data Data of the Task to be re-constructed.
     * @return The Task corresponding to the data.
     */
    public static Task loadTask(String data) {
        String[] dataSplit = data.split(" \\| ", 4);
        char c = dataSplit[0].charAt(0);
        boolean isDone = dataSplit[1].equals("1");
        String description = dataSplit[2];
        LocalDate time = dataSplit.length == 4 ? LocalDate.parse(dataSplit[3]) : null;

        switch (c) {
        case 'D':
            return new Deadline(description, isDone, time);
        case 'E':
            return new Event(description, isDone, time);
        default:
            return new Todo(description, isDone);
        }
    }

    /**
     * Converts the Task to data to be saved.
     *
     * @return Data representing the Task.
     */
    public String saveTask() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }
}
