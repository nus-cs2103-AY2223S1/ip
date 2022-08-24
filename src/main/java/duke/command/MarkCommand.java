package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private int toMark;

    public MarkCommand(int toMark) {
        this.toMark = toMark - 1;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        try {
            task.markTask(this.toMark);
            Task taskToMark = task.getTask(toMark);
            ui.displayMarkTask(taskToMark);
        } catch (IndexOutOfBoundsException e) {
            ui.displayInvalidTaskIndex();
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
