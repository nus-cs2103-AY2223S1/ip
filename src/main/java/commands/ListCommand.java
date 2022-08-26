package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Prints the current tasks in the list
 */
public class ListCommand extends Command {
    /**
     * Lists the current tasks
     * <p>
     * {@inheritDoc}
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.getTask(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
