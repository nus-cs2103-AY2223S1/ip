package duke.task;

import java.time.LocalDate;

/**
 * Represents a Task.
 * @author Aaron Pang
 * @version CS2103T AY22/23 Semester 1
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs duke.task.Task with its description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     *
     * @return The status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " " ); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Loads the task.
     * @param data the data to be loaded
     * @return Tasks that have been loaded
     */
    public static Task loadTask(String data) {
        String[] input = data.split( " \\| ", 4);
        char c = input[0].charAt(0);
        boolean isDone = input[1].equals("1");
        String description = input[2];
        LocalDate time = input.length == 4 ? LocalDate.parse(input[3]) : null;

        if (c == 'D') {
            return new Deadline(description, isDone, time);
        } else if (c == 'E') {
            return new Event(description, isDone, time);
        } else {
            return new Todo(description, isDone);
        }
    }

    /**
     * Saves the task
     * @return The string message of the saved task
     */
    public String saveTask() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Shows the description and status of the task.
     *
     * @return String with the description and status of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.getDescription() ;
    }
}
