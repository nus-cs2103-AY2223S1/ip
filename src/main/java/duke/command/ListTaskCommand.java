package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.task.TaskList;

/**
 * Represents a command to list the contents of task list.
 */
public class ListTaskCommand extends Command {
    private static final ListTaskCommand LIST_TASK_COMMAND = new ListTaskCommand();

    /**
     * Returns the list task command.
     *
     * @return list task command.
     */
    public static ListTaskCommand of() {
        return LIST_TASK_COMMAND;
    }

    /**
     * Returns String representation of task list.
     *
     * @param taskList task list.
     * @param clientList client list.
     * @return String representation of task list.
     * @throws DukeException if no tasks.
     */
    @Override
    public String execute(TaskList taskList, ClientList clientList) throws DukeException {
        return CommandOutputs.showTaskList(taskList);
    }
}
