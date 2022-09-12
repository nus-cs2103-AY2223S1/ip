package duke.command;

import duke.TaskList;

/**
 * Abstract class representing a Command with TaskList and the commands array.
 */
public abstract class CommandWithTasklistAndCommands extends Command {
    /**
     * The tasks list.
     */
    protected final TaskList taskList;
    /**
     * The arguments.
     */
    protected final String[] arguments;

    /**
     * Returns an instance of the class
     * @param taskList The tasks list
     * @param arguments The arguments
     */
    public CommandWithTasklistAndCommands(TaskList taskList, String[] arguments) {
        this.taskList = taskList;
        this.arguments = arguments;
    }
}
