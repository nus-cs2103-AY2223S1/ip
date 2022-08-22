package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private final int taskIndex;

    public UnmarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsNotDone(taskIndex);
        ui.showUnmarkTask(tasks.getTask(taskIndex));
        storage.write(tasks);
    }
}
