package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.CliUi;

/**
 * A command class that handles an error that occurs during the phase of parsing.
 * The difference between an error command and an unknown command is that the former can be interpreted by the parser,
 * but causes some errors such as index out of bound, illegal date and tiem format etc.
 */
public class ErrorCommand extends Command {

    private final String errorMessage;

    /**
     * The standard constructor.
     */
    public ErrorCommand(String errorMessage) {
        super(CommandType.ERROR);
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the command concretely.
     * Handles an error that occurs during the phase of parsing.
     * Prints the error message.
     *
     * @param cliUi An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     */
    @Override
    protected void executeConcretely(CliUi cliUi, TaskList taskList, Storage storage) {
        cliUi.printOutput(errorMessage);
    }
}
