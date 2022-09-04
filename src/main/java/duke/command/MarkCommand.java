package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;


/**
 * The MarkCommand class represents a command
 * that marks a task in duke.Duke as done.
 */
public class MarkCommand extends Command {
    /** The numbers of the tasks to be marked as done. */
    private final Integer[] numbers;

    /**
     * Constructs a new MarkCommand.
     *
     * @param numbers The numbers (as displayed in the full list) of the tasks to be marked as done.
     */
    public MarkCommand(Integer[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Marks tasks in Duke as done.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message that a task has been marked as done.
     * @throws DukeException when the command cannot be successfully executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> markedTasks = tasks.mark(numbers);
        storage.writeToFile(tasks);
        return ui.showMarked(markedTasks);
    }
}
