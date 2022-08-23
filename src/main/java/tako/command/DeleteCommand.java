package tako.command;

import java.io.IOException;

import tako.Storage;
import tako.TakoException;
import tako.TaskList;
import tako.Ui;

import tako.task.Task;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for DeleteCommand with number of the task to delete.
     *
     * @param taskNumber Position of the task to delete in the task list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task from the task list.
     * The task list is saved after that.
     *
     * @param tasks Task list.
     * @param ui Ui.
     * @param storage Task storage.
     * @throws IOException If the task list fails to save to the file.
     * @throws TakoException If the task to delete does not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TakoException {
        Task task = tasks.remove(taskNumber);
        storage.saveToFile(tasks);
        ui.showDelete(task, tasks.getSize());
    }

    /**
     * Returns false as Tako cannot exit after this command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
