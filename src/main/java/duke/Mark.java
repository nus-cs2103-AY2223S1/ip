package duke;

/**
 * Represents the Mark command.
 */
public class Mark extends Task {
    public String command;
    public Mark(String description) {
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
        return "Nice! I've marked this task as done:\n" + this.command.substring(0, 3) + "[X]" + this.command.substring(6);
    }
}
