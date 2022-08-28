package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command{

    /**
     * Exits from duke and closes the application.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
        storage.write(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
