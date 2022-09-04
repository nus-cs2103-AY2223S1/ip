package duke.task;

import java.time.LocalDate;

import duke.DukeException;

/**
 * Task encapsulates a task to be tracked in the TaskList.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * A constructor for Task.
     *
     * @param description The description of the Task.
     * @param isDone Has the Task been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Updates the date (if any) for the Task.
     *
     * @param newDate The new date for the Task.
     */
    public abstract void updateDate(LocalDate newDate);

    /**
     * Generates the status icon of the Task depending on whether it is done or not.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Converts boolean to int.
     *
     * @return The int representation of whether the Task is done.
     */
    public int isDoneToInt() {
        return isDone ? 1 : 0;
    }

    /**
     * Sets the Task to be done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the Task to be undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Checks if a Task description contains the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return Is the keyword in the Task description.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Creates a specific Task type given the input details.
     *
     * @param data The String formatted data of the Task retrieved from the Storage.
     * @return A specific type of Task created from the data.
     * @throws DukeException If Task type from the data is unrecognised.
     */
    public static Task loadToTaskList(String data) throws DukeException {
        String[] dataSplit = data.split(" \\| ", 4);
        char typeOfTask = dataSplit[0].trim().charAt(0);
        boolean isDone = dataSplit[1].equals("1");
        String description = dataSplit[2];
        switch (typeOfTask) {
        case 'T':
            return new ToDo(description, isDone);
        case 'D':
            return new Deadline(description, isDone, LocalDate.parse(dataSplit[3]));
        case 'E':
            return new Event(description, isDone, LocalDate.parse(dataSplit[3]));
        default:
            throw new DukeException("OOPS!!! Unrecognised task type!");
        }
    }

    /**
     * Converts the Task into the String format required to be saved in the Storage.
     *
     * @return String formatted data of the Task.
     */
    public String saveStringFormat() {
        return String.format("%d | %s", isDoneToInt(), description);
    }

    /**
     * Converts the Task into its String representation.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}
