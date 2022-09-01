package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int command) {
        index = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.mark(index);
            storage.mark(index);
            ui.showMessage("Good job! Task " + index + " has been completed:\n  " + tasks.showTask(index));
        } catch (IndexOutOfBoundsException e) {
            ui.showError("You've given me an invalid task to mark!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
