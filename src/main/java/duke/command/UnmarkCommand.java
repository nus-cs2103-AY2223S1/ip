package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Encapsulates a command to mark a Task as incomplete.
 */
public class UnmarkCommand extends Command {
    /** Stores index of Task to unmark. */
    int taskNo;

    /**
     * Constructor for UnmarkCommand.
     * @param taskNo task index
     */
    public UnmarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.unmark(taskNo);
            storage.write(tasks.toStringWritable());
            return "Ok, I've marked this task as not done yet:\n" +
                "\t" + tasks.getTask(taskNo).toString();
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("Invalid task index to unmark.");
        }
    }
}
