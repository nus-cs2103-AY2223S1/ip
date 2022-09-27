package duke.command;

/**
 * Represents the Command to add Tasks to TaskList.
 */
public abstract class AddCommand extends Command {
    protected String msg;

    /**
     * Constructs the class.
     * @param action Action to add specified Task.
     * @param message The information of the Task.
     */
    protected AddCommand(Action action, String message) {
        super(action);
        this.msg = message;
    }

    /**
     * Returns whether this command terminates Duke.
     * @return Returns whether this command terminates Duke.
     */
    @Override
    public boolean isTerminating() {
        return false;
    }
}
