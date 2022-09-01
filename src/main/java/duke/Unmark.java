package duke;

/**
 * Represents the unmark command.
 */
public class Unmark extends Task {
    private String command;
    public Unmark(String description) {
        super(description);
        this.command = description;
    }

    /**
     * Checks if the command should be added to the list.
     *
     * @return boolean
     */
    @Override
    public boolean AddToList() {
        return false;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "OK, I've marked this task as not done yet:\n" + this.command.substring(0, 3) + "[ ]" + this.command.substring(6);
    }
}
