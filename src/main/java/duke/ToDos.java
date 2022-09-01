package duke;

/**
 * Represents the todo command.
 */
public class ToDos extends Task {
    private String command;
    private int num;

    public ToDos(String description, int num) {
        super(description);
        this.command = description;
        this.num = num;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "[T][ ] " + this.command + "\n" + "Now you have " + this.num + " tasks in the list.";
    }
}
