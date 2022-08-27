package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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
     * @param ui An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     */
    @Override
    protected void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        String output = taskList.getListInfo();
        ui.printOutput(output);
    }
}
