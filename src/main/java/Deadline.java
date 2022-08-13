/**
 * Encapsulate Deadline which is-a Task
 *
 * @author: Jonas Png
 */
public class Deadline extends Task{

    protected String by;

    /**
     * Class constructor for Deadline.
     *
     * @param description Deadline's description.
     * @param by Deadline's by.
     * @throws DukeException if Deadline's by is empty.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The deadline needs to have a end day and/or date");
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
