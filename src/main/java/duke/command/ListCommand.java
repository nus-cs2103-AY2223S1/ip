package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("===============================");
        tasks.listTasks();
        System.out.println("===============================");
    }

    /**
     * Returns false, because deadline is not an application terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
