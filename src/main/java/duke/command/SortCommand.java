package duke.command;

import static duke.Duke.TAB;

import duke.ui.CliUi;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A command class that sorts all tasks in the list by chronological order from earlier to later.
 */
public class SortCommand extends Command {

    private static final String OUTPUT_MESSAGE = "Sure! I have sorted the list!";

    /**
     * The standard constructor.
     */
    public SortCommand() {
        super(CommandType.SORT);
    }

    /**
     * Executes the command concretely.
     * Sorts all the tasks.
     *
     * @param cliUi An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     * @return String to be displayed on the screen as a response to the user input.
     */
    @Override
    protected String executeConcretely(CliUi cliUi, TaskList taskList, Storage storage) {
        String output = OUTPUT_MESSAGE;
        taskList.sort();
        output += "\n" + TAB + taskList.getListInfo();

        cliUi.printOutput(output);

        super.saveFile(cliUi, taskList, storage);
        return output;
    }
}
