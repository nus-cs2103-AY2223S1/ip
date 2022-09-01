package duke.command;

import duke.exception.DukeCommandAlreadyExecutedException;
import duke.exception.DukeIoException;
import duke.util.CliUi;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * The base class of all other command types. It has a contract that requires the execute() method
 * to be implemented concretely.
 */
public abstract class Command {

    private static final String COMMAND_ALREADY_EXECUTED_ERROR_MESSAGE = "Oops! This command has been executed";
    private boolean isExecuted;
    private final CommandType commandType;

    /**
     * The standard constructor.
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
        isExecuted = false;
    }

    /**
     * Returns true if and only if this command is an exit command.
     * If true, then the main listening loop in Duke terminates.
     * @return A boolean that indicates whether this command is an exit command.
     */
    public boolean isExit() {
        return commandType == CommandType.EXIT;
    }

    /**
     * Executes the command. The command will follow prescribed instructions depending on their command type.
     * This method will invoke the concrete method overridden by its subclasses.
     * This method itself checks if a command is executed twice.
     *
     * @param cliUi An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     */
    public void execute(CliUi cliUi, TaskList taskList, Storage storage) throws DukeCommandAlreadyExecutedException {
        if (isExecuted) {
            throw new DukeCommandAlreadyExecutedException(COMMAND_ALREADY_EXECUTED_ERROR_MESSAGE);
        }
        isExecuted = true;
        executeConcretely(cliUi, taskList, storage);
    }

    /**
     * Executes the command concretely. The command will follow prescribed instructions depending on their command type.
     *
     * @param cliUi An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     */
    protected abstract void executeConcretely(CliUi cliUi, TaskList taskList, Storage storage);

    /**
     * Makes use of a Storage object to save the TaskList.
     *
     * @param cliUi An object that prints error messages in case of failure.
     * @param taskList The list to be saved.
     * @param storage An object that writes the file.
     */
    protected void saveFile(CliUi cliUi, TaskList taskList, Storage storage) {
        try {
            storage.saveFile(taskList.getFileStream());
        } catch (DukeIoException exception) {
            cliUi.printOutput(exception.getMessage());
        }
    }
}
