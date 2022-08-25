package duke.command;

/**
 * Represents the Command to add Tasks to TaskList.
 */
public abstract class AddCommand extends Command {
    /**
     * The information of the Task.
     */
    protected String msg;

    /**
     * Constructor of the Add Command.
     * @param action Action to add specified Task.
     * @param message The information of the Task.
     */
    protected AddCommand(Action action, String message) {
        super(action);
        this.msg = message;
    }

    /**
     * Returns the information of the Task.
     * @return The information of the Task.
     */
    public String getMsg() {
        return this.msg;
    }

    /**
     * Returns whether this command terminates Duke.
     * @return Returns whether this command terminates Duke.
     */
    @Override
    public boolean isTerminated() {
        return false;
    }
}
