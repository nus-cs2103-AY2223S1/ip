package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException {
        Task task = taskList.get(index);
        task.markAsIncomplete();
        storage.save(taskList);
    }
}