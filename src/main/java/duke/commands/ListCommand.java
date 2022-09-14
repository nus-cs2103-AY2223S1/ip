package duke.commands;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;

public class ListCommand extends Command{

    public ListCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    public String listTasks() {
        ArrayList<Task> list = taskList.listTasks();
        return ui.listTasks(taskList);
    }
}
