package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command encapsulates an index value and marks the Task as completed
 * at the encapsulated index in the task list when executed.
 */
public class MarkCommand extends Command {

    /** The keyword to run MarkCommand. */
    public static final String COMMAND_NAME = "mark";

    private final int markIndex;

    /**
     * Constructs a MarkCommand object encapsulating the specified index value.
     *
     * @param markIndex The index of the Task to be marked as completed.
     */
    public MarkCommand(int markIndex) {
        this.markIndex = markIndex;
    }

    /**
     * Marks the Task as completed at the encapsulated index in the specified TaskList object.
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
            if (tasks.getTask(this.markIndex).getIsDone()) {
                ui.showReply(String.format("Sorry, but it seems you have marked this task as done:\n  %s",
                        tasks.getTask(markIndex)));
            } else {
                tasks.getTask(this.markIndex).setDone(true);
                ui.showReply(String.format("Noice! I've marked this task as done:\n  %s", tasks.getTask(markIndex)));
            }
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I do not have a task with that number in my list.", e);
        }
    }

    /**
     * Returns false as MarkCommand is not a terminating Command.
     *
     * @return false.
     */
    @Override
    public boolean isTerminator() {
        return false;
    }
}
