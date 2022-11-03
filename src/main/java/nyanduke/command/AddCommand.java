package nyanduke.command;

import nyanduke.NyanDukeException;
import nyanduke.Storage;
import nyanduke.Ui;
import nyanduke.task.Task;
import nyanduke.task.TaskList;

/**
 * The AddCommand class represents a command
 * that adds a new task to NyanDuke's task list.
 */
public class AddCommand extends Command {
    /** The task to be added. */
    private final Task task;

    /**
     * Constructs a new AddCommand.
     *
     * @param task The task to be added to NyanDuke.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to NyanDuke.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message that a task has been added to NyanDuke.
     * @throws NyanDukeException when the command cannot be successfully executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NyanDukeException {
        tasks.add(task);
        storage.writeToFile(tasks);
        return ui.showAdded(task, tasks);
    }
}

