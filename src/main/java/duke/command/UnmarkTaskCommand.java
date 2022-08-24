package duke.command;

import duke.task.TasksController;
import duke.Ui;
import duke.Storage;
import duke.exception.NoSuchTaskException;
/**
 * UnmarkTaskCommand will execute the command of unmarking a task.
 */
public class UnmarkTaskCommand extends Command {

    /**
     * Unmarks a task
     * @param controller Duke task controller
     * @param ui Duke UI
     * @param storage Duke IO processor
     */
    public void execute(TasksController controller, Ui ui, Storage storage) {
        ui.prompt("Please type in the task index that you want to unmark:");
        int taskIndexToUnmark = ui.inputTask() - 1;
        try {
            controller.changeTaskStatus(taskIndexToUnmark, false);
            String reply = controller.getTask(taskIndexToUnmark).toString();
            ui.display(reply, false, false, true, false);
        } catch (NoSuchTaskException e) {
            ui.reportError("The task you choose does not exist! Please try again...");
            ui.showSplitLine();
        }
    }
}
