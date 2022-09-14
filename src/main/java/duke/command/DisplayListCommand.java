package duke.command;

import duke.ui.CliUi;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A command class that displays all tasks in the list.
 */
public class DisplayListCommand extends Command {

    /**
     * The standard constructor.
     */
    public DisplayListCommand() {
        super(CommandType.DISPLAY_LIST);
    }

    /**
     * Executes the command concretely.
     * Displays all tasks in the list.
     *
     * @param cliUi An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     * @return String to be displayed on the screen as a response to the user input.
     */
    @Override
    protected String executeConcretely(CliUi cliUi, TaskList taskList, Storage storage) {
        String output = taskList.getListInfo();
        cliUi.printOutput(output);
        return output;
    }
}
