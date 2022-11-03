package nyanduke.command;

import java.util.ArrayList;

import nyanduke.NyanDukeException;
import nyanduke.Storage;
import nyanduke.Ui;
import nyanduke.task.Task;
import nyanduke.task.TaskList;

/**
 * The UnmarkCommand class represents a command
 * that marks a task in NyanDuke as done.
 */
public class UnmarkCommand extends Command {
    /** The number indices of the tasks to be marked as not done. */
    private final Integer[] numbers;

    /**
     * Constructs a new UnmarkCommand.
     *
     * @param numbers The number indices (as displayed by the "list" command)
     *                of the tasks to be marked as not done.
     */
    public UnmarkCommand(Integer[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Marks tasks in NyanDuke as not done.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message that a task has been marked as not done.
     * @throws NyanDukeException when the command cannot be successfully executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NyanDukeException {
        ArrayList<Task> unmarkedTasks = tasks.unmark(numbers);
        storage.writeToFile(tasks);
        return ui.showNotMarked(unmarkedTasks);
    }
}
