package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.CliUi;

/**
 * A command class that handles every input that cannot be interpreted by the parser.
 */
public class UnknownCommand extends Command {

    private static final String GENERAL_ERROR_STRING = "Sorry, I don't understand that!";

    /**
     * The standard constructor.
     */
    public UnknownCommand() {
        super(CommandType.UNKNOWN);
    }

    /**
     * Executes the command concretely.
     * Handles input that cannot be interpreted by the parser.
     * Prints a message saying that the command cannot be parsed.
     *
     * @param cliUi An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     */
    @Override
    protected void executeConcretely(CliUi cliUi, TaskList taskList, Storage storage) {
        cliUi.printOutput(GENERAL_ERROR_STRING);
    }
}
