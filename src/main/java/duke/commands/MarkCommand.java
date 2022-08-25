package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.List;
import duke.task.Task;
import duke.ui.Ui;

public class MarkCommand extends Command {

    public static final String MARK_COMMAND = "mark";

    public static final String MESSAGE_SUCCESS = "Congrats on completing this task!\n"
            + "%1$s" + "\n"
            + "You have " + "%2$s" + " tasks in the list.\n";

    public int taskToMark;
    private Task task;

    public MarkCommand(int taskToMark) {
        this.taskToMark = taskToMark;
    }

    @Override
    public void execute(List tasks, Ui ui, Storage storage) {
        try {
            task = tasks.getTask(taskToMark);
            task.markTaskAsDone();
            storage.save();
            ui.showToUser(String.format(MESSAGE_SUCCESS, task, tasks.numberOfTasks()));
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}