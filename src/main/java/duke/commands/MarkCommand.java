package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Task task = tasks.getTask(taskIndex);
        task.markAsDone();
        storage.writeAllTasksToFile(tasks);
        ui.showMarkTaskMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
