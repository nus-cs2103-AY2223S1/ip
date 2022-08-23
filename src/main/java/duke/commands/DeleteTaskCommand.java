package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteTaskCommand extends Command {
    int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException {
        Task task = taskList.delete(index);
        ui.showMessage(String.format("Noted. I've removed this task:\n\t%s\n", task));
        storage.save(taskList);
    }
}