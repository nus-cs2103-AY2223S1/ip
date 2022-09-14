package duke.commands;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    public DeleteCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    public String deleteTask(ArrayList<String> parsedInput) {
        Task task = taskList.delete(Integer.parseInt(parsedInput.get(1)));
        storage.saveTask(taskList);
        return ui.printDeletedTask(task, taskList);
    }
}
