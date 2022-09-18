package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Class which manages marking tasks.
 */
public class MarkCommand extends Command {

    private String number;

    /**
     * Creates an instance of mark command.
     * @param command task number based on task list
     */
    public MarkCommand(String command) {
        number = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(number);
            tasks.mark(index);
            storage.mark(index);
            ui.showMessage("Good job! Task " + index + " has been completed:\n  " + tasks.showTask(index));
        } catch (NumberFormatException e) {
            ui.showError("You need to provide a task's index to mark!");
        } catch (IndexOutOfBoundsException e) {
            ui.showError("You've given me an invalid task to mark!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
