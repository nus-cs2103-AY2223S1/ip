package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Prints an overview of all added tasks and their status.
 *
 * @author sikai00
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() <= 0) {
            String msg = "You currently have no task in your list! Great "
                    + "job for completing all your tasks :-)";
            ui.prettyPrint(msg);
        } else {
            String msgBegin = "Here are the tasks in your list:\n";
            String msg = msgBegin + taskList;
            ui.prettyPrint(msg);
        }
    }
}
