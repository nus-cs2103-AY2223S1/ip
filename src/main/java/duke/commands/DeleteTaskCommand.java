package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task from the task list.
 */
public class DeleteTaskCommand extends Command {
    private final int index;

    /**
     * Constructor for DeleteTaskCommand.
     * @param index the index specifying the task to be deleted.
     */
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
