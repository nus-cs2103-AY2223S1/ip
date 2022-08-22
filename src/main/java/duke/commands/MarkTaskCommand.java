package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private final int taskIndex;

    public MarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsDone(taskIndex);
        ui.showMarkTask(tasks.getTask(taskIndex));
        storage.write(tasks);
    }
}
