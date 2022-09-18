package manmeowth.command;

import manmeowth.list.TaskList;
import manmeowth.storage.ListLoader;
import manmeowth.ui.Ui;

/**
 * Represents an input command to the ManMeowth application.
 *
 * @author WR3nd3
 */
public abstract class Command {

    /**
     * Executes relevant procedures to this command to the task list, UI, and list loader.
     *
     * @param tasks list.manmeowth.TaskList containing the tasks on the list.
     * @param ui ui.manmeowth.Ui that interacts with the input of users and output from manmeowth.ManMeowth.
     * @param storage storage.manmeowth.ListLoader that updates list.manmeowth.TaskList information on a separate file.
     * @return String containing manmeowth's response to the command being executed.
     */
    public abstract String execute(TaskList tasks, Ui ui, ListLoader storage);

    /**
     * Returns whether the command is an exit command.
     *
     * @return Boolean on whether the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
