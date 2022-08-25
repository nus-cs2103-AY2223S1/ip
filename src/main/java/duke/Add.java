package duke;

/**
 * Represents the Add command.
 */
public class Add extends Task {
    public String command;
    public Add(String description) {
        super(description);
        this.command = description;
    }

    /**
     * Returns a String representing the command Add.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[ ][ ] " + this.command;
    }
}
