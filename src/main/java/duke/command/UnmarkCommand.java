package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to unmark tasks as done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates UnmarkCommand.
     * @param index Index of task to mark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks task as not done and prints it to user.
     * Also saves the updated tasks to storage.
     * @param tasks List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmarkTask(index);
        storage.saveFile(tasks);

        return "OK, I've marked this task as not done yet:" + '\n'
                + "  " + task;

    }
}
