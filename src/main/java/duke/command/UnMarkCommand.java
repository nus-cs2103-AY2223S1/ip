package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.task.TaskList;

/**
 * Represents a command to unmark a task in task list.
 */
public class UnMarkCommand extends Command {

    private final int index;

    /**
     * Constructs a UnMark Command object.
     *
     * @param index index of task in task list.
     */
    public UnMarkCommand(int index) {
        assert index >= 0;
        this.index = index;
    }

    /**
     * Unmarks task in task list and saves it.
     *
     * @param taskList task list.
     * @param clientList client list.
     * @return nothing.
     * @throws DukeException if task is already unmarked or index does not exist.
     */
    @Override
    public String execute(TaskList taskList, ClientList clientList) throws DukeException {
        try {
            if (!taskList.get(index).isDone()) { //Guard Clause
                throw new DukeException("Task is already unmarked");
            }
            taskList.get(index).unmarkTask();
            SaveTaskListCommand.of().execute(taskList, clientList);
            return CommandOutputs.showUnmark(taskList, index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Index %d does not exist on the list.", index + 1)); //plus 1 for indexing
        }
    }

}
