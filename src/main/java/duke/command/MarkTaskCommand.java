package duke.command;

import duke.task.TasksController;
import duke.Ui;
import duke.Storage;
import duke.exception.NoSuchTaskException;
public class MarkTaskCommand extends Command {

    public void execute(TasksController controller, Ui ui, Storage storage) {
        ui.prompt("Please type in the task index that you want to mark:");
        int taskIndexToMark = ui.inputTask() - 1;
        try {
            controller.changeTaskStatus(taskIndexToMark, true);
            String reply = controller.getTask(taskIndexToMark).toString();
            ui.display(reply, false, true, false, false);
        } catch (NoSuchTaskException e) {
            ui.reportError("The task you choose does not exist! Please try again...");
        }
    }
}
