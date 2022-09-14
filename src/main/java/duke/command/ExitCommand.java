package duke.command;

import duke.ui.CliUi;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A command class that terminates the main listening loop of main() in Duke.
 */
public class ExitCommand extends Command {

    private static final String EXIT_OUTPUT_STRING = "Bye! See you next time!";

    /**
     * The standard constructor.
     */
    public ExitCommand() {
        super(CommandType.EXIT);
    }

    /**
     * Executes the command concretely.
     * Prints the exit message.
     *
     * @param cliUi An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     * @return String to be displayed on the screen as a response to the user input.
     */
    @Override
    protected String executeConcretely(CliUi cliUi, TaskList taskList, Storage storage) {
        cliUi.printOutput(EXIT_OUTPUT_STRING);
        return EXIT_OUTPUT_STRING;
    }
}
