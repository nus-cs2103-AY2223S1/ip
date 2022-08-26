package duke;

public class Event extends Task {

    // Attributes of a duke.Event
    protected String at;

    /**
     * The constructor for the Event
     * @param description the description of the event
     * @param at the time of the event
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        if (description.equals("")) {
            throw new DukeException("The description of a event cannot be empty.");
        }
    }

    /**
     * The string representation of the event
     * @return a string representing the description and the time of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * A string representation to write to the file
     * @return a string that represents what to write to the file
     */
    public String saveToDisk() {
        String alreadyDone = super.getStatusIcon();
        return "E | " + alreadyDone + " | " + super.getDescription() + "\n";
    }

}
