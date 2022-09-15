package duke.command;

import duke.errors.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to delete tasks. Command contains index of task to be deleted.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand
     * @param index integer of task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command
     * @param tasks TaskList object that stores tasks
     * @param ui Ui object deals with user interaction
     * @param storage Storage object that handles text file
     * @throws DukeException exception thrown in TaskList, Ui or Storage methods
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(index);
        ui.deleteSuccess(task, tasks);
        storage.save(tasks);
    }
}
