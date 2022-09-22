package duke.task;

import java.time.LocalDate;

/**
 * A class that encapsulates a todo task.
 */
public class ToDo extends Task {

    /**
     * The class constructor.
     *
     * @param description The description of the event.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string to be saved in the text file to be
     * loaded in later.
     *
     * @return The string identifying the details of the task.
     */
    @Override
    public String stringToSave() {
        String statusString = "X".equals(super.getStatusIcon()) ? "1|" : "0|";
        return "T|" + statusString + super.description;
    }

    /**
     * Returns the date associated with the task.
     *
     * @return The date associated with the task.
     */
    @Override
    public LocalDate getDate() {
        return null;
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }

    /**
     * Compares this task with the specified task for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param task The task to be compared.
     * @return A negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Task task) {
        LocalDate taskDate = task.getDate();
        if (taskDate == null) {
            return 0;
        } else {
            return 1;
        }
    }
}
