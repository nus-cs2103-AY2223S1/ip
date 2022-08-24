package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private int toUnmark;

    public UnmarkCommand(int toUnmark) {
        this.toUnmark = toUnmark - 1;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        try {
            task.unmarkTask(toUnmark);
            Task taskToUnmark = task.getTask(toUnmark);
            ui.displayUnmarkTask(taskToUnmark);
        } catch (IndexOutOfBoundsException e) {
            ui.displayInvalidTaskIndex();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
