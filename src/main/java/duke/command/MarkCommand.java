package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Checks the task to be completed.
 *
 * @author Bryan Ng Zi Hao
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for MarkCommand.
     *
     * @param index The task of the said index to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task as done.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @return String representation of Duke's reply.
     * @throws DukeException There is an error in execution.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(index);
        task.markAsDone();
        String output = " Nice! I've marked this task as done:\n";
        output += task;
        return output;
    }

    /**
     * Checks if this command will result in the bot to stop running.
     *
     * @return a boolean value.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
