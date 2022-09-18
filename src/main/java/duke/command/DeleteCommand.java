package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    private String number;

    public DeleteCommand(String command) {
        number = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(number);
            String taskS = "tasks";
            if (tasks.listSize() - 1 == 1) {
                taskS = "task";
            }
            ui.showMessage("Got it! Task " + index + " has been deleted from the list:\n  "
                    + tasks.showTask(index)
                    + "\nYou have a total of " + (tasks.listSize() - 1) + " " + taskS + " in the list.");
            tasks.delete(index);
            storage.delete(index);
        } catch (NumberFormatException e) {
            ui.showError("You need to provide a task's index to delete!");
        } catch (IndexOutOfBoundsException e) {
            ui.showError("You've given me an invalid task to delete!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
