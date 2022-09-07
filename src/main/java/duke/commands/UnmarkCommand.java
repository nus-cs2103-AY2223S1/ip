package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Task task = tasks.getTask(this.taskIndex);
        task.maskUndone();
        storage.writeAllTasksToFile(tasks);
        ui.showUnmarkTaskMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
