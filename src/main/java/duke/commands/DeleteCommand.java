package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Represents an executable command that adds a Task to the specified TaskList.
 */
public class DeleteCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "delete";
    private final int taskIndex;

    /**
     * Initializes a new DeleteCommand instance.
     *
     * @param taskIndex Index of the task to be added
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
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

        storage.writeAllToStorage(taskList);
        CommandResult cr = new CommandResult(msg);
        return cr;
    }
}
