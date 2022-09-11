package duke.task;

import java.time.LocalDate;

import duke.DukeException;

/**
 * ToDo is a Task that has no date.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class ToDo extends Task {
    /**
     * A constructor for ToDo.
     *
     * @param description The description of the ToDo.
     * @param isDone Has the ToDo been completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }


    /**
     * Throws a DukeException as there is no date to update for ToDo.
     *
     * @param newDate The new date for the Task.
     */
    @Override
    public void updateDate(LocalDate newDate) {
        throw new DukeException("You can only update Events and Deadlines!");
    }

    /**
     * Converts the ToDo into the String format required to be saved in the Storage.
     *
     * @return String formatted data of the ToDo.
     */
    @Override
    public String saveStringFormat() {
        return String.format("T | %s", super.saveStringFormat());
    }

    /**
     * Converts the ToDo into its String representation.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return " [T]" + super.toString();
    }
}
