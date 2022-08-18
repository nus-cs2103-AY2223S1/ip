package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class ToDoCommand extends Command {
    public static final String COMMAND_ID = "TODO";
    private final Task task;

    public ToDoCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTaskToList(task);
        storage.writeDataToFile(taskList);
    }
}
