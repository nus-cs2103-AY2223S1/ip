package duke;

/**
 * Represents the Event command.
 */
public class Events extends Task {
    public String command;
    public int num;
    public String time;

    public Events(String description, int num, String time) {
        super(description);
        this.command = description;
        this.num = num;
        this.time = time;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "[E][ ] " + this.command + "(at: " + time + ")\n"
                + "Now you have " + this.num + " tasks in the list.";
    }
}
