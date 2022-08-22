package duke.task;

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
     * A method that converts the ToDo into the String format required to be saved in the Storage.
     *
     * @return String formatted data of the ToDo.
     */
    @Override
    public String saveStringFormat() {
        return String.format("T | %s", super.saveStringFormat());
    }

    /**
     * A method that converts the ToDo into its String representation.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
