package duke.command;

import duke.task.TasksController;
import duke.Ui;
import duke.Storage;
/**
 * ShowTasksCommand will execute the command of showing all tasks.
 */
public class ShowTasksCommand extends Command {

    /**
     * Shows all tasks
     * @param controller Duke task controller
     * @param ui Duke UI
     * @param storage Duke IO processor
     */
    public void execute(TasksController controller, Ui ui, Storage storage) {
        ui.display(controller.toString(), true, false, false, false);
    }
}
