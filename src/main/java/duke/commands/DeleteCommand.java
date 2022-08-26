package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Delete a task and prints a confirmation message.
 *
 * @author sikai00
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.taskIndex > taskList.size() - 1 || this.taskIndex < 0) {
            throw new DukeException("There is no such task index... "
                    + "Try 'list' to view all the tasks and their index!");
        }
        String task = taskList.getTask(taskIndex).toString();
        taskList.deleteTask(taskIndex);

        int size = taskList.size();
        String taskString = size > 1 ? "tasks" : "task";
        String msgBegin = "Noted. I've removed this task:\n";
        String msgEnd = "\nNow you have " + size + " " + taskString
                + " in this list.";
        String msg = msgBegin + " " + task + msgEnd;
        ui.prettyPrint(msg);

        Storage.writeAllToStorage(taskList);
    }
}
