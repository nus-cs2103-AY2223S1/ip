package duke.command;

import duke.gui.Ui;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents the command that is executed when the user inputs list.
 *
 * @author njxue
 * @version v0.1
 */
public class ListCommand extends Command {
    /**
     * Executes the list command. Prints the list of tasks from the TaskList.
     *
     * @param tasks TaskList to be printed.
     * @param ui Ui object which interacts with the user.
     * @param storage Storage object which loads and saves tasks.
     * @return Message to be shown to the user after successful execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getPrettyTaskList(tasks);
    }

    /**
     * Returns the format of the list command.
     *
     * @return The format of the list command.
     */
    public static String getFormat() {
        return "list";
    }

    /**
     * Returns false, because list is not an application terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
