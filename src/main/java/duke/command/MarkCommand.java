package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to mark tasks as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates mark command.
     * @param index Index of task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks task as done and prints it to user.
     * Also saves the updated tasks to storage.
     * @param tasks List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.markTask(index);
        storage.saveFile(tasks);
        return "Nice! I've marked this task as done:" + '\n'
                + "  " + task;
    }
}
