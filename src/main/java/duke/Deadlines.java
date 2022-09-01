package duke;

/**
 * Represents the Deadlines command.
 */
public class Deadlines extends Task {
    private String command;
    private int num;
    private String time;

    public Deadlines(String description, int num, String time) {
        super(description);
        this.command = description;
        this.num = num;
        this.time = time;
    }

    /**
     * Returns a String representing the command Deadlines.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "[D][ ] " + this.command + "(by: " + time + ")\n"
                + "Now you have " + this.num + " tasks in the list.";
    }
}
