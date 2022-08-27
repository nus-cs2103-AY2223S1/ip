package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

public class InvalidCommand extends Command {
    /**
     * Executes the command for unrecognised user inputs.
     * This is the main way for outputting bot replies.
     *
     * @param storage the storage object
     * @param tasklist the task list object
     * @param ui the user interface object
     */
    public void execute(Storage storage, TaskList tasklist, Ui ui) {
        System.out.println(ui.SPACER + "\n"
                + "Sorry, I don't understand. T^T\n"
                + "Please start your command with list, mark, unmark, todo, deadline, event or bye. :')\n"
                + ui.SPACER);
    }
}