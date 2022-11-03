package duke.task;

/**
 * The Event class which represents an event task.
 *
 * @author Leong Jia Hao Daniel
 */
public class Event extends Task {

    protected String time;

    /**
     * Constructs the Event task.
     *
     * @param description The description of the event.
     * @param time The time of the event.
     */
    public Event(String description, String time) {
        super(description);
        assert !description.isEmpty();
        this.time = time;
    }

    public Event() {}

    /**
     * Reads from the input file and returns a event based on the data
     * in the file.
     *
     * @param string The string from the input file.
     * @return A new event task describing the event.
     */
    public static Event parseFile(String string) {
        String[] details = string.split(" \\| ");
        Event event = new Event(details[2], details[3]);
        if (details[1].equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    /**
     * Overrides the toDataFormat() in task to return a String which
     * is stored in the file.
     *
     * @return The task but formatted in the way it is meant to
     *         be stored in the file.
     */
    @Override
    public String toDataFormat() {
        String completed = "0";
        if (getStatusIcon().equals("X")) {
            completed = "1";
        }
        return "E | " + completed + " | " + this.getDescription() + " | " + this.time;
    }

    /**
     * Overrides the toString() method to display the task to the user.
     *
     * @return A String representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
