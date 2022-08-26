package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Represents an executable command prints an overview of all added tasks and their status.
 */
public class ListCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "list";

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() <= 0) {
            String msg = "You currently have no task in your list! Great "
                    + "job for completing all your tasks :-)";
            ui.prettyPrint(msg);
        } else {
            String msgBegin = "Here are the tasks in your list:\n";
            String msg = msgBegin + taskList.toString();
            ui.prettyPrint(msg);
        }
    }
}
