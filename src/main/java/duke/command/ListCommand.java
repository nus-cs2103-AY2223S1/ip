package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskArr = tasks.getTaskList();
        ui.showList(taskArr);
    }
}
