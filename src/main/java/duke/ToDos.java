package duke;

/**
 * Represents the todo command.
 */
public class ToDos extends Command {
    private String command;
    private int num;

    public ToDos(String description, int num) {
        this.command = description;
        this.num = num;
    }

    @Override
    public void execute() {
    }

    /**
     * Checks if the command should be added to the list.
     *
     * @return boolean
     */
    @Override
    public boolean addToList() {
        return true;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "[T][ ] " + this.command + "\n" + "Now you have " + this.num
                + " tasks in the list.";
    }
}
