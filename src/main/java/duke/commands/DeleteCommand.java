package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.getTask(index);
            taskList.deleteTask(index);
            storage.saveToFile(taskList);
            ui.deleteTask(index, task);
        } catch (DukeException e) {
            ui.printException(e);
        }
    }
}
