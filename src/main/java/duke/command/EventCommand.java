package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class EventCommand extends Command {
    public static final String COMMAND_ID = "EVENT";
    private final Task task;

    public EventCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        String result = taskList.addTaskToList(task);
        storage.writeDataToFile(taskList);
        return result;
    }
}
