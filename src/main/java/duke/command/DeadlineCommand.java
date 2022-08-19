package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeadlineCommand extends Command {
    public static final String COMMAND_ID = "DEADLINE";
    private final Task task;

    public DeadlineCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTaskToList(task);
        storage.writeDataToFile(taskList);
    }
}
