package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String read(TaskList taskList, Ui ui, Storage storage) {
        int numOfTasks = taskList.getNumOfTasks();
        String result = ui.getDeleteMessage(taskList.readTask(index), numOfTasks - 1);
        taskList.deleteTask(index);
        return result;
    }
}
