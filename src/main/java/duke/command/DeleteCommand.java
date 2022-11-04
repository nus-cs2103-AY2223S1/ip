package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command encapsulates an index value and deletes
 * the Task at the encapsulated index in the task list when executed.
 */
public class DeleteCommand extends Command {

    /** The keyword to run DeleteCommand. */
    public static final String COMMAND_NAME = "delete";

    private final int delIndex;

    /**
     * Constructs a DeleteCommand object encapsulating the specified index value.
     *
     * @param delIndex The index of the Task to be deleted.
     */
    public DeleteCommand(int delIndex) {
        this.delIndex = delIndex;
    }

    /**
     * Deletes the Task at the encapsulated index in the specified TaskList object.
     * <p>
     * In addition, the appropriate response will be sent to the specified Ui parameter
     * and the specified Storage parameter will also be updated with the new TaskList values.
     *
     * @param tasks the specified TaskList object.
     * @param ui the specified Ui object.
     * @param storage the specified Storage object.
     * @throws DukeException if the provided index value is out-of-bounds,
     *     or if tasks are unable to be saved into Storage.
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task delTask = tasks.getTask(this.delIndex);
            tasks.deleteTask(this.delIndex);
            ui.showReply(String.format("Understood, I've deleted the following task:\n"
                    + "  %s\nYou now have %d tasks remaining.\n", delTask, tasks.getLength()));
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I do not have a task with that number in my list.", e);
        }
    }

    /**
     * Returns false as DeleteCommand is not a terminating Command.
     *
     * @return false.
     */
    @Override
    public boolean isTerminator() {
        return false;
    }
}
