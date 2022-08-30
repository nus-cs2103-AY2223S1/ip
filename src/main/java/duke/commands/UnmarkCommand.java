package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Represents an executable command that marks the task as not done.
 */
public class UnmarkCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "unmark";
    private final int taskIndex;

    /**
     * Initializes a new UnmarkCommand instance.
     *
     * @param taskIndex Task index to be added
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.taskIndex > taskList.size() - 1 || this.taskIndex < 0) {
            throw new DukeException("There is no such task index... "
                    + "Try 'list' to view all the tasks and their index!");
        }
        taskList.unmarkTask(this.taskIndex);

        String msgBegin = "OK, I've marked this task as not done yet:\n ";
        String msg = msgBegin + taskList.getTask(taskIndex);

        storage.writeAllToStorage(taskList);
        return msg;
    }
}
