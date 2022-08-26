package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Mark the task with the input index as not done and prints a confirmation
 * message.
 *
 * @author sikai00
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.taskIndex > taskList.size() - 1 || this.taskIndex < 0) {
            throw new DukeException("There is no such task index... "
                    + "Try 'list' to view all the tasks and their index!");
        }
        taskList.unmarkTask(this.taskIndex);

        String msgBegin = "OK, I've marked this task as not done yet:\n ";
        String msg = msgBegin + taskList.getTask(taskIndex);
        ui.prettyPrint(msg);

        Storage.writeAllToStorage(taskList);
    }
}
