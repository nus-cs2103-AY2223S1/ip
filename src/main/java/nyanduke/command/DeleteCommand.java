package nyanduke.command;

import java.util.ArrayList;

import nyanduke.NyanDukeException;
import nyanduke.Storage;
import nyanduke.Ui;
import nyanduke.task.Task;
import nyanduke.task.TaskList;

/**
 * The DeleteCommand class represents a command
 * that will delete a task from NyanDuke's task list.
 */
public class DeleteCommand extends Command {
    /** The number indices of the tasks to be deleted. */
    private final Integer[] numbers;

    /**
     * Constructs a new DeleteCommand.
     *
     * @param numbers The number indices (as displayed by the "list" command)
     *                of the tasks to be deleted.
     */
    public DeleteCommand(Integer[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Deletes tasks from NyanDuke.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message that a task was deleted from NyanDuke.
     * @throws NyanDukeException when the command cannot be successfully executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NyanDukeException {
        ArrayList<Task> deletedTasks = tasks.delete(numbers);
        storage.writeToFile(tasks);
        return ui.showDeleted(deletedTasks, tasks);
    }
}
