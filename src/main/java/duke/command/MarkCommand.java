package duke.command;

import duke.errors.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to mark task. Command contains index of task to be marked.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for Mark command
     * @param index task to be marked
     */
    public MarkCommand(int index) {
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
        tasks.get(index).finished();
        Task task = tasks.get(index);
        ui.showMark(task);
        storage.save(tasks);
    }
}
