package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The DeleteCommand class represents a command
 * that will delete a task from Duke's task list.
 */
public class DeleteCommand extends Command {
    /** The numbers of the tasks to be deleted. */
    private final Integer[] numbers;

    /**
     * Constructs a new DeleteCommand.
     *
     * @param numbers The numbers (as displayed in the full list) of the task to be deleted.
     */
    public DeleteCommand(Integer[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Deletes tasks from Duke.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message that a task was deleted from Duke.
     * @throws DukeException when the command cannot be successfully executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> deletedTasks = tasks.delete(numbers);
        storage.writeToFile(tasks);
        return ui.showDeleted(deletedTasks, tasks);
    }
}
