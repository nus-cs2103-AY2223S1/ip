package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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
     * @param ui An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     */
    @Override
    protected void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        ui.printOutput(EXIT_OUTPUT_STRING);
    }
}
