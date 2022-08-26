package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Mark the task with the input index as done and prints a confirmation
 * message.
 *
 * @author sikai 00
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.taskIndex > taskList.size() - 1 || this.taskIndex < 0) {
            throw new DukeException("There is no such task index... "
                    + "Try 'list' to view all the tasks and their index!");
        }
        taskList.markTask(this.taskIndex);

        String msgBegin = "Nice! I've marked this task as done:\n ";
        String msg = msgBegin + taskList.getTask(this.taskIndex);
        ui.prettyPrint(msg);

        Storage.writeAllToStorage(taskList);
    }
}
