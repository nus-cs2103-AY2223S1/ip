package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ExitCommand saves the task list information and exits the program.
 */
public class ExitCommand extends Command{

    /**
     * Exits from duke and closes the application.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @return String output to be displayed by duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.write(tasks);
        return "Bye. Hope to see you again soon!\n";
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
