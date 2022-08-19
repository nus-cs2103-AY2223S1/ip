/**
 * Encapsulate Event which is-a Task.
 *
 * @author: Jonas Png
 */
public class Event extends Task {

    protected String at;

    /**
     * Class constructor for Event.
     *
     * @param description Event's description.
     * @param at Event's at.
     * @throws DukeException if event's at is empty.
     */
    public Event(String description, String at) throws DukeException{
        super(description);
        if (at.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The event needs to have specific start time and end time");
        }
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String toStorageString() {
        return "E" + super.toStorageString() + " | " + this.at;
    }

}
