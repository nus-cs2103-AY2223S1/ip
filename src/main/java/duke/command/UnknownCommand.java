package duke.command;

import duke.ui.CliUi;
import duke.util.Storage;
import duke.util.TaskList;

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
     * @return String to be displayed on the screen as a response to the user input.
     */
    @Override
    protected String executeConcretely(CliUi cliUi, TaskList taskList, Storage storage) {
        cliUi.printOutput(GENERAL_ERROR_STRING);
        return GENERAL_ERROR_STRING;
    }
}
