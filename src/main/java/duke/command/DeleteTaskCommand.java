package duke.command;

import duke.task.TasksController;
import duke.Ui;
import duke.Storage;
import duke.exception.NoSuchTaskException;

public class DeleteTaskCommand extends Command {

    public void execute(TasksController controller, Ui ui, Storage storage) {
        ui.prompt("Please type in the task index that you want to delete:");
        int taskIndexToMark = ui.inputTask() - 1;
        try {
            controller.deleteFromList(taskIndexToMark);
            ui.display("", false, false, false, true);
        } catch (NoSuchTaskException e) {
            ui.reportError("The task you want to delete does not exist! Please try again...");
        }
    }

}
