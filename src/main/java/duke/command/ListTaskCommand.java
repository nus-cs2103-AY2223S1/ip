package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a command to list the contents of task list.
 */
public class ListTaskCommand extends Command {
    private static final ListTaskCommand LIST_COMMAND = new ListTaskCommand();

    /**
     * Returns the list command.
     *
     * @return list command.
     */
    public static ListTaskCommand of() {
        return LIST_COMMAND;
    }

    /**
     * Tells user interface to print task list.
     *
     * @param taskList task list.
     * @param storage  files storing task list.
     * @return
     * @throws DukeException if list is empty.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, ClientList clientList) throws DukeException {
        return CommandOutputs.showListOut(taskList);
    }
}
