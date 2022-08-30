package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The AddCommand class represents a command
 * that adds a new task to Duke's task list.
 */
public class AddCommand extends Command {
    /** The task to be added to Duke */
    private final Task task;

    /**
     * Constructs a new AddCommand.
     *
     * @param task The task to be added to Duke.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to Duke.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @throws DukeException when the command cannot be successfully executed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        storage.writeToFile(tasks);
        ui.showAdded(task, tasks);
    }
}

