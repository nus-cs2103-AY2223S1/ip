package nyanduke.command;

import java.util.ArrayList;

import nyanduke.NyanDukeException;
import nyanduke.Storage;
import nyanduke.Ui;
import nyanduke.task.Task;
import nyanduke.task.TaskList;


/**
 * The MarkCommand class represents a command
 * that marks a task in duke.Duke as done.
 */
public class MarkCommand extends Command {
    /** The number indices of the tasks to be marked as done. */
    private final Integer[] numbers;

    /**
     * Constructs a new MarkCommand.
     *
     * @param numbers The number indices (as displayed by the "list" command)
     *                of the tasks to be marked as done.
     */
    public MarkCommand(Integer[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Marks tasks in NyanDuke as done.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message that a task has been marked as done.
     * @throws NyanDukeException when the command cannot be successfully executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NyanDukeException {
        ArrayList<Task> markedTasks = tasks.mark(numbers);
        storage.writeToFile(tasks);
        return ui.showMarked(markedTasks);
    }
}
