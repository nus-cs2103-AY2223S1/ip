package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The UnmarkCommand class represents a command
 * that marks a task in Duke as done.
 */
public class UnmarkCommand extends Command {
    /** The numbers of the tasks to be marked as not done. */
    private final Integer[] numbers;

    /**
     * Constructs a new UnmarkCommand.
     *
     * @param numbers The numbers (as displayed in the full list) of the tasks to be marked as not done.
     */
    public UnmarkCommand(Integer[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Marks tasks in Duke as not done.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message that a task has been marked as not done.
     * @throws DukeException when the command cannot be successfully executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> unmarkedTasks = tasks.unmark(numbers);
        storage.writeToFile(tasks);
        return ui.showNotMarked(unmarkedTasks);
    }
}
