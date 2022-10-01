package duke.command;

/**
 * Abstract class representing a Command with TaskList and the commands array.
 */
public abstract class CommandWithTasklistAndCommands extends Command {
    /**
     * The arguments.
     */
    protected final String[] arguments;

    /**
     * Returns an instance of the class
     * @param arguments The arguments
     */
    public CommandWithTasklistAndCommands(String[] arguments) {
        this.arguments = arguments;
    }
}
